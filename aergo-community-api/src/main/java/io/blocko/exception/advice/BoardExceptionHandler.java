package io.blocko.exception.advice;

import org.springframework.http.HttpStatus;
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
	@ExceptionHandler(BoardNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBoardNotFoundException(BoardNotFoundException ex) {
		return "redirect:/main";
	}
	
	@ExceptionHandler(RestBoardNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResultForm handleRestBoardNotFoundException(RestBoardNotFoundException ex) {
		return ResultForm.of(ex.getMessage(), 200, false);
	}
	
	@ExceptionHandler(BoardFileUploadException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResultForm handleBoardFileUploadException(BoardFileUploadException ex) {
		return ResultForm.of(ex.getMessage(), 201, false);
	}
	
	@ExceptionHandler(BoardFileDownloadException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBoardFileDownloadException(BoardFileDownloadException ex) {
		return "redirect:/main";
	}
	

}
