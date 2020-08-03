package io.blocko.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.blocko.exception.UserDuplicationException;
import io.blocko.form.ResultForm;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserDuplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserDuplicationException(UserDuplicationException ex) {
		return new ResultForm(ex.getMessage() + "은 중복된 아이디입니다.", 101, false);
	}
}
