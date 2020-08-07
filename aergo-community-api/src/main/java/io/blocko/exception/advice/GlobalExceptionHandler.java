package io.blocko.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import io.blocko.form.ResultForm;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResultForm handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
		return ResultForm.of("파일 업로드 최대 용량을 초과했습니다.", 400, false);
	}
}
