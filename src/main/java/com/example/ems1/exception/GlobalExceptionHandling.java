package com.example.ems1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> invalidata(MethodArgumentNotValidException exception) {
		String message=exception.getBindingResult().getFieldError().getDefaultMessage();
		return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidOtpException.class)
	public ResponseEntity<String> InvalidOtp(InvalidOtpException invalidOtpException) {
		return new ResponseEntity<String>(invalidOtpException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(VerifedException.class)
	public ResponseEntity<String> verifedotp(VerifedException verifedException) {
		return new ResponseEntity<String>(verifedException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}
