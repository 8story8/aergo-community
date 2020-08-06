package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String email) {
		super(email + "은 존재하지 않은 사용자입니다.");
	}
}
