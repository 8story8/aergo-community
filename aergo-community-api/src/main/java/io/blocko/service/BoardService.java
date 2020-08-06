package io.blocko.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.blocko.dto.BoardRegistrationDto;
import io.blocko.exception.BoardFileUploadException;
import io.blocko.model.Board;
import io.blocko.model.SimpleUser;
import io.blocko.repository.BoardRepository;

@Service
@Transactional
public class BoardService {

	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	private BoardRepository boardRepository;

	public Board register(BoardRegistrationDto registrationDto, SimpleUser user) {
		Board board = null;
		final String title = registrationDto.getTitle();
		final String content = registrationDto.getContent();
		final MultipartFile file = registrationDto.getFile();

		final String fileOriginName = file.getOriginalFilename();

		final String fileExtName = fileOriginName.substring(fileOriginName.lastIndexOf("."), fileOriginName.length());

		try {
			final String filePath = uploadPath + "/" + UUID.randomUUID().toString().replace("-", "")
					+ fileExtName;
			file.transferTo(new File(filePath));
			board = boardRepository.save(new Board(title, content, fileOriginName, filePath, user));
		} catch (Exception e) {
			throw new BoardFileUploadException(fileOriginName);
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
		pageable = PageRequest.of(page, 10, Sort.by("id").descending());
		return boardRepository.findAll(pageable);
	}

	public void updateViewCount(Board board) {
		final Integer currentViewCount = board.getViewCount();
		final Integer updatedViewCount = currentViewCount + 1;
		board.setViewCount(updatedViewCount);
		boardRepository.save(board);
	}

	public void download(Board board, HttpServletResponse response) {
		final File file = new File(board.getFilePath());

		try {
			final String fileName = new String(board.getFileName().getBytes(), "iso-8859-1");

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", "application/octet-stream;");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentLength((int) file.length());

			final OutputStream out = response.getOutputStream();

			Files.copy(file.toPath(), out);

			out.flush();
		} catch (IOException e) {
			throw new BoardFileUploadException(board.getFileName());
		}
	}

}
