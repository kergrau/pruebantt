package com.example.pruebantt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {

	ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

	private String roleName;
}
