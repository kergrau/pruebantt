package com.example.pruebantt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebantt.dtos.LoginRequestDto;
import com.example.pruebantt.dtos.SignUpRequestDto;
import com.example.pruebantt.dtos.UserInfoResponseDto;
import com.example.pruebantt.entities.Role;
import com.example.pruebantt.entities.User;
import com.example.pruebantt.enums.ECustomError;
import com.example.pruebantt.enums.ERole;
import com.example.pruebantt.exceptions.CustomException;
import com.example.pruebantt.repositories.RoleRepository;
import com.example.pruebantt.repositories.UserRepository;
import com.example.pruebantt.security.jwt.JwtUtils;
import com.example.pruebantt.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).toList();

		String token = jwtUtils.generateToken(userDetails.getUsername(), roles);
		// ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		// return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
		// jwtCookie.toString()).body(
		// new UserInfoResponseDto( userDetails.getUsername(),
		// userDetails.getEmail(), token);
		return new ResponseEntity<>(new UserInfoResponseDto(userDetails.getUsername(), userDetails.getEmail(), token),
				HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new CustomException(ECustomError.USERNAME_TAKEN, HttpStatus.CONFLICT);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new CustomException(ECustomError.EMAIL_TAKEN, HttpStatus.CONFLICT);
		}

		// Create new user's account
		User user = new User();
		user = user.toEntity(signUpRequest);
		user.setPassword(encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();

		Role userRole = roleRepository.findByName(ERole.ROLE_USER.getRoleName())
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);

		user.setRoles(roles);
		User userSaved = userRepository.save(user);
		return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new String("You've been signed out!"));
	}
}
