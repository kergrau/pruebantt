package com.example.pruebantt.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

	private final String error;
	private final String message;
}
