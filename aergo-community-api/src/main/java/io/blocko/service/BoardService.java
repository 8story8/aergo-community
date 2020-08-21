package io.blocko.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.blocko.dto.BoardRegistrationDto;
import io.blocko.dto.BoardUpdateDto;
import io.blocko.exception.BoardFileUploadException;
import io.blocko.exception.BoardUpdateStatusNotFoundException;
import io.blocko.exception.RestBoardNotFoundException;
import io.blocko.model.Board;
import io.blocko.model.SimpleUser;
import io.blocko.page.Pager;
import io.blocko.repository.BoardRepository;

@Service
@Transactional
public class BoardService {

	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	private BoardRepository boardRepository;

	public Board register(BoardRegistrationDto registrationDto, SimpleUser user) {
		final String title = registrationDto.getTitle();
		final String content = registrationDto.getContent();
		final MultipartFile file = registrationDto.getFile();
		return registerInternal(title, content, file, user);
	}

	public Board registerInternal(String title, String content, MultipartFile file, SimpleUser user) {
		Board board = null;
		if (!file.isEmpty()) {
			final String fileOriginName = file.getOriginalFilename();

			final String fileExtName = fileOriginName.substring(fileOriginName.lastIndexOf("."),
					fileOriginName.length());
			try {
				final String filePath = uploadPath + "/" + UUID.randomUUID().toString().replace("-", "") + fileExtName;
				file.transferTo(new File(filePath));
				board = boardRepository.save(new Board(title, content, fileOriginName, filePath, 0, user));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BoardFileUploadException(fileOriginName);
			}
		} else {
			board = boardRepository.save(new Board(title, content, 0, user));
		}

		return board;
	}

	public Board update(BoardUpdateDto updateDto, SimpleUser user) {
		Board board = findById(updateDto.getId()).orElseThrow(() -> new RestBoardNotFoundException());

		final String title = updateDto.getTitle();
		final String content = updateDto.getContent();
		final MultipartFile file = updateDto.getFile();

		// 원래 파일을 가지고 있는 게시물이면
		if (updateDto.getHasAlreadyFile().equals("true")) {
			final String updateStatus = updateDto.getUploadStatus();

			// 초기 상태이면
			if (updateStatus.equals("Init")) {
				board.setTitle(title);
				board.setContent(content);
				board = boardRepository.save(board);
				// 파일을 삭제한 상태이면
			} else if (updateStatus.equals("Delete")) {
				final File originFile = new File(board.getFilePath());
				originFile.delete();

				board.setTitle(title);
				board.setContent(content);
				board.setFileName(null);
				board.setFilePath(null);
				board = boardRepository.save(board);
				// 파일을 삭제하고 다른 파일을 업로드한 상태이면
			} else if (updateStatus.equals("Upload")) {
				final File originFile = new File(board.getFilePath());
				originFile.delete();

				final String fileName = file.getOriginalFilename();
				final String fileExtName = fileName.substring(fileName.lastIndexOf("."), fileName.length());

				try {
					final String filePath = uploadPath + "/" + UUID.randomUUID().toString().replace("-", "")
							+ fileExtName;
					file.transferTo(new File(filePath));

					board.setTitle(title);
					board.setContent(content);
					board.setFileName(fileName);
					board.setFilePath(filePath);
					board = boardRepository.save(board);
				} catch (Exception e) {
					throw new BoardFileUploadException(fileName);
				}

			} else {
				throw new BoardUpdateStatusNotFoundException();
			}

			// 원래 파일을 가지고 있지 않은 게시물이면
		} else {
			final String updateStatus = updateDto.getUploadStatus();
			// 초기 상태이면
			if (updateStatus.equals("Init")) {
				board.setTitle(title);
				board.setContent(content);
				board = boardRepository.save(board);
				// 파일을 업로드한 상태이면
			} else if (updateStatus.equals("Upload")) {
				final String fileName = file.getOriginalFilename();
				final String fileExtName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				try {
					final String filePath = uploadPath + "/" + UUID.randomUUID().toString().replace("-", "")
							+ fileExtName;
					file.transferTo(new File(filePath));

					board.setTitle(title);
					board.setContent(content);
					board.setFileName(fileName);
					board.setFilePath(filePath);
					board = boardRepository.save(board);
				} catch (Exception e) {
					throw new BoardFileUploadException(fileName);
				}
			} else {
				throw new BoardUpdateStatusNotFoundException();
			}
		}

		return board;
	}

	public void delete(Board board) {
		if (board.getFilePath() != null) {
			final File file = new File(board.getFilePath());
			file.delete();
		}
		boardRepository.deleteById(board.getId());
	}

	public Optional<Board> findById(Long id) {
		return boardRepository.findById(id);
	}

	public Pager<Board> findAll(int page) {

		page = page > 0 ? page - 1 : 0;
		
		if (page != 0) {
			long totalPage = boardRepository.count() / Pager.SIZE;
			if (page > totalPage) {
				page = (int) totalPage;
			}
		}

		final Page<Board> boardPages = boardRepository.findAll(PageRequest.of((int) page, Pager.SIZE, Sort.by("id").descending()));
		
		return Pager.of(boardPages.getContent(), boardPages);
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
