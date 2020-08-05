package io.blocko.exception.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.blocko.controller.UserController;
import io.blocko.exception.UserDuplicationException;
import io.blocko.exception.UserPasswordNotEqualsException;
import io.blocko.form.ResultForm;

@ControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleBindException(BindException ex) {
		return createBindExceptionResultForm(ex.getFieldErrors());
	}

	private ResultForm createBindExceptionResultForm(List<FieldError> fieldErrors) {
		for (FieldError fieldError : fieldErrors) {
			final String objectName = fieldError.getObjectName();
			final String code = fieldError.getCode();
			if (objectName.equals("userRegistrationDto")) {
				if (code.equals("Email")) {
					return ResultForm.of("이메일 형식을 확인해주세요.", 101, false);
				}
				if (code.equals("NotBlank")) {
					return ResultForm.of("값을 입력해주세요.", 102, false);
				}
			} else if (objectName.equals("userLoginDto")) {
				if (code.equals("NotBlank")) {
					return ResultForm.of("값을 입력해주세요.", 102, false);
				}
			}
		}

		return ResultForm.of("관리자에게 문의하세요.", 110, false);
	}

	@ExceptionHandler(UserDuplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserDuplicationException(UserDuplicationException ex) {
		return ResultForm.of(ex.getMessage(), 104, false);
	}

	@ExceptionHandler(UserPasswordNotEqualsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserPasswordNotEqualsException(UserPasswordNotEqualsException ex) {
		return ResultForm.of(ex.getMessage(), 105, false);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.FOUND)
	private String handleUsernameNotFoundException(UsernameNotFoundException ex) {
		return "redirect:/";
	}
}