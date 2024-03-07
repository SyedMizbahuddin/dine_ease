package com.mizbah.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mizbah.dto.ErrorResponse;
import com.mizbah.exception.DuplicateEntityFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ExceptionInterceptor {

	@ExceptionHandler(DuplicateEntityFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleDuplicateEntityFoundException(DuplicateEntityFoundException e) {

		log.error("Got DuplicateEntityFoundException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
