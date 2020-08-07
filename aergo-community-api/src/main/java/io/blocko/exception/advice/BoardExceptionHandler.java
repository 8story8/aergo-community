package io.blocko.exception.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.blocko.controller.BoardController;
import io.blocko.exception.BoardFileDownloadException;
import io.blocko.exception.BoardFileUploadException;
import io.blocko.exception.BoardNotFoundException;
import io.blocko.exception.RestBoardNotFoundException;
import io.blocko.form.ResultForm;
import io.blocko.service.BoardService;

@ControllerAdvice(basePackageClasses = {BoardController.class, BoardService.class})
public class BoardExceptionHandler {
	
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
			if (objectName.equals("boardRegistrationDto")) {
				if (code.equals("Email")) {
					return ResultForm.of("이메일 형식을 확인해주세요.", 600, false);
				}
				if (code.equals("NotBlank")) {
					return ResultForm.of("값을 입력해주세요.", 601, false);
				}
			} else if (objectName.equals("boardUpdateDto")) {
				if (code.equals("Email")) {
					return ResultForm.of("이메일 형식을 확인해주세요.", 600, false);
				}
				if (code.equals("NotBlank")) {
					return ResultForm.of("값을 입력해주세요.", 601, false);
				}
			}
		}

		return ResultForm.of("관리자에게 문의하세요.", 610, false);
	}
	
	
	@ExceptionHandler(BoardNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBoardNotFoundException(BoardNotFoundException ex) {
		return "redirect:/main";
	}
	
	@ExceptionHandler(RestBoardNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResultForm handleRestBoardNotFoundException(RestBoardNotFoundException ex) {
		return ResultForm.of(ex.getMessage(), 602, false);
	}
	
	@ExceptionHandler(BoardFileUploadException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResultForm handleBoardFileUploadException(BoardFileUploadException ex) {
		return ResultForm.of(ex.getMessage(), 603, false);
	}
	
	@ExceptionHandler(BoardFileDownloadException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBoardFileDownloadException(BoardFileDownloadException ex) {
		return "redirect:/main";
	}
	

}
