package com.example.pruebantt.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.pruebantt.enums.ECustomError;

@RestControllerAdvice
public class HandlerException {

	private static Logger _logger = LoggerFactory.getLogger(HandlerException.class);

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ExceptionResponse> handlerCustomException(CustomException customException) {
		_logger.error("Error Handler CustomException ----> " + customException.getError().getErrorCode() + " "
				+ customException.getError().getMessage());
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(customException.getError().getErrorCode(),
				customException.getError().getMessage()), customException.getHttpStatus());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ExceptionResponse> handlerCustomException(AccessDeniedException customException) {
		_logger.error("Error Handler CustomException ----> " + customException.getCause() + " "
				+ customException.getMessage());
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(ECustomError.ACCESS_DENIED.getErrorCode(),
				ECustomError.ACCESS_DENIED.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handlerUnknownException(Exception exception) {
		_logger.error("Error NO Handler Exception ----> " + exception.getCause() + " " + exception.getMessage());
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(ECustomError.UNEXPECTED_ERROR.getErrorCode(),
				ECustomError.UNEXPECTED_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
