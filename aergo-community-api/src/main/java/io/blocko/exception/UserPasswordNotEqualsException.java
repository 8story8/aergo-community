package io.blocko.exception;

public class UserPasswordNotEqualsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserPasswordNotEqualsException() {
		super("비밀번호가 일치하지 않습니다.");
	}
}
