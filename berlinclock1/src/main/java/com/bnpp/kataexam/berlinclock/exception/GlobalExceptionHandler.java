package com.bnpp.kataexam.berlinclock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bnpp.kataexam.berlinclock.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TimeFormatException.class)
	public ResponseEntity<ErrorResponse> handleTimeFormatException(TimeFormatException ex) {

		ErrorResponse errorResponse	= ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST.value())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse errorResponse	= ErrorResponse.builder()
						.message("An unexpected error occurred.")
						.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
