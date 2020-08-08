package io.blocko.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.blocko.controller.UserController;
import io.blocko.exception.UserDuplicationException;
import io.blocko.exception.UserNotFoundException;
import io.blocko.exception.UserPasswordNotEqualsException;
import io.blocko.form.ResultForm;
import io.blocko.service.UserService;

@ControllerAdvice(basePackageClasses = {UserController.class, UserService.class})
public class UserExceptionHandler {

	@ExceptionHandler(UserDuplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserDuplicationException(UserDuplicationException ex) {
		return ResultForm.of(ex.getMessage(), 500, false);
	}

	@ExceptionHandler(UserPasswordNotEqualsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserPasswordNotEqualsException(UserPasswordNotEqualsException ex) {
		return ResultForm.of(ex.getMessage(), 501, false);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.FOUND)
	private String handleUsernameNotFoundException(UsernameNotFoundException ex) {
		return "redirect:/";
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserFoundException(UserNotFoundException ex) {
		return ResultForm.of(ex.getMessage(), 502, false);
	}
}
