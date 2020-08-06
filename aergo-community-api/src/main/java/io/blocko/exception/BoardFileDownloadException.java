package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardFileDownloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoardFileDownloadException(String fileName) {
		super(fileName + "을 확인해주세요.");
	}
}
