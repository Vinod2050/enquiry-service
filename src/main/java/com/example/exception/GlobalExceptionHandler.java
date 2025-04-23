package com.example.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<String>NullPointerException(NullPointerException ne){
		String message = ne.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value=NoSuchElementException.class)
	public ResponseEntity<String>NoSuchElementException(java.util.NoSuchElementException nse){
		String message = nse.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.NO_CONTENT);
	}
	
	 @ExceptionHandler(NumberFormatException.class)
	    public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
		 String message = ex.getMessage();
	        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> Exception(Exception ex) {
		 String message = ex.getMessage();
	        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	    }
	
	
	
	
}

