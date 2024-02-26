package com.example.common.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.common.exception.CustomException.DuplicateUserException;
import com.example.common.exception.CustomException.MenuServiceException;
import com.example.common.exception.CustomException.OrderServiceException;
import com.example.common.exception.CustomException.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateUserException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<String> handleDuplicateEntityException(DuplicateUserException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleEntityNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MenuServiceException.class)
	public ResponseEntity<Map<String, Object>> handleMenuServiceException(MenuServiceException ex, WebRequest request) {

		Map<String, Object> errorResponse = Collections.singletonMap("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(OrderServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleOrderServiceException(OrderServiceException e) {

		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing the order: " + e.getMessage());
	}
}
