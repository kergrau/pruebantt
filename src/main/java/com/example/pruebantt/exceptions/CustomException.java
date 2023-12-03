package com.example.pruebantt.exceptions;

import org.springframework.http.HttpStatus;

import com.example.pruebantt.enums.ECustomError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 2916503321764325274L;
	private final ECustomError error;
	private final HttpStatus httpStatus;
}
