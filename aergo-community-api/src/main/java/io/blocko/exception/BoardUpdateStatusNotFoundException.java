package io.blocko.exception;

public class BoardUpdateStatusNotFoundException extends RuntimeException{ 

	private static final long serialVersionUID = 1L;

	public BoardUpdateStatusNotFoundException() {
		super("관리자에게 문의하세요.");
	}
	
	public BoardUpdateStatusNotFoundException(String msg) {
		super(msg);
	}
}
