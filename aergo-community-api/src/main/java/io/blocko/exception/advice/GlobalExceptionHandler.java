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
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import io.blocko.exception.UserNotFoundException;
import io.blocko.form.ResultForm;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.FOUND)
	private String handleUsernameNotFoundException(UsernameNotFoundException ex) {
		return "redirect:/";
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleUserNotFoundException(UserNotFoundException ex) {
		return ResultForm.of(ex.getMessage(), 502, false);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResultForm handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
		return ResultForm.of("파일 업로드 최대 용량을 초과했습니다.", 600, false);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private ResultForm handleBindException(BindException ex) {
		return createBindExceptionResultForm(ex.getFieldErrors());
	}

	private ResultForm createBindExceptionResultForm(List<FieldError> fieldErrors) {
		for (FieldError fieldError : fieldErrors) {
			final String code = fieldError.getCode();

			if (code.equals("Email")) {
				return ResultForm.of("이메일 형식을 확인해주세요.", 900, false);
			}
			if (code.equals("NotBlank")) {
				return ResultForm.of("값을 입력해주세요.", 901, false);
			}
		}

		return ResultForm.of("관리자에게 문의하세요.", 1000, false);
	}
}
