package io.blocko.exception;

public class BoardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoardNotFoundException() {
		super("해당 게시물은 존재하지 않습니다.");
	}
}
