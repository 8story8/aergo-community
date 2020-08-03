package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDuplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserDuplicationException(String msg) {
		super(msg);
	}
}
