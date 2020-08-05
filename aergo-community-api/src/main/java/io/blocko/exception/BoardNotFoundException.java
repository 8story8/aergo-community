package io.blocko.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoardNotFoundException(Long id) {
		super(id + "에 해당하는 게시물은 존재하지 않습니.");
	}
}
