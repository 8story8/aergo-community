package io.blocko.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.blocko.dto.BoardRegistrationDto;
import io.blocko.model.Board;
import io.blocko.model.SimpleUser;
import io.blocko.repository.BoardRepository;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public Board register(BoardRegistrationDto registrationDto, SimpleUser user) {
		Board board = null;
		final String title = registrationDto.getTitle();
		final String content = registrationDto.getContent();
		final MultipartFile file = registrationDto.getFile();

		try {
			file.getOriginalFilename();
			board = boardRepository.save(new Board(title, content, "1", user));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}
	
	public void delete(Long id) {
		boardRepository.deleteById(id);
	}
	
	public Optional<Board> findById(Long id) {
		return boardRepository.findById(id);
	}

	public Page<Board> findAll(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10);
		return boardRepository.findAll(pageable);
	}
	
	public void updateViewCount(Board board) {
		final Integer currentViewCount = board.getViewCount();
		final Integer updatedViewCount = currentViewCount+1;
		board.setViewCount(updatedViewCount);
		boardRepository.save(board);
	}
	
	
}
