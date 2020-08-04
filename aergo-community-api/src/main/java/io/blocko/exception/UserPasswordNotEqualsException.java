package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserPasswordNotEqualsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserPasswordNotEqualsException(String msg) {
		super(msg);
	}
}
