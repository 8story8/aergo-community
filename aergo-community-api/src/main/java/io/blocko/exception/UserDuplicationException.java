package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDuplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserDuplicationException(String email) {
		super(email + "은 중복된 아이디입니다.");
	}
}
