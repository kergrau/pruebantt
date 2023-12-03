package com.example.pruebantt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

	private String username;
	private String email;
	private String token;
}
