package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RestBoardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RestBoardNotFoundException(Long id) {
		super(id + "에 해당하는 게시물은 존재하지 않습니다.");
	}
}
