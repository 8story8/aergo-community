package io.blocko.service;

import java.sql.Blob;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.blocko.dto.BoardRegistrationDto;
import io.blocko.model.Board;
import io.blocko.model.SimpleUser;
import io.blocko.repository.BoardRepository;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public Board register(SimpleUser user, BoardRegistrationDto registrationDto) {
		final String title = registrationDto.getTitle();
		final String content = registrationDto.getContent();
		final Blob file = registrationDto.getFile();
		final Board board = boardRepository.save(new Board(title, content, user));
		return board;
	}
	
	public Page<Board> findAll(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10);
		return boardRepository.findAll(pageable);
	}
}
