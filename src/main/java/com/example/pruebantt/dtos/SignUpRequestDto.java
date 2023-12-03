package com.example.pruebantt.dtos;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

	@NotBlank(message = "Campo no puede ir vacio")
	@Email
	private String email;
	@NotBlank(message = "Campo no puede ir vacio")
	private String username;
	@NotBlank(message = "Campo no puede ir vacio")
	private String password;
	private Set<String> roles;
}
