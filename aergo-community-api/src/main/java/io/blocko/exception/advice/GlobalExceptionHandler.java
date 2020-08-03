package io.blocko.exception.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.blocko.form.ResultForm;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleConstraintViolationException(ConstraintViolationException ex) {
		final String message = ex.getMessage();
		
		return new ResultForm("", 1, false);
	}
	
	private ResultForm getResultForm() {
		return new ResultForm("", 1, false);
	}
}
