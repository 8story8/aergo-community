package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardFileUploadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoardFileUploadException(String fileName) {
		super(fileName + "을 확인해주세요.");
	}
}
