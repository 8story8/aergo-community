package io.blocko.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.dto.BoardRegistrationDto;
import io.blocko.dto.BoardUpdateDto;
import io.blocko.dto.UserDto;
import io.blocko.exception.BoardNotFoundException;
import io.blocko.exception.RestBoardNotFoundException;
import io.blocko.exception.UserNotFoundException;
import io.blocko.form.ResultForm;
import io.blocko.model.Board;
import io.blocko.model.SimpleUser;
import io.blocko.service.BoardService;
import io.blocko.service.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;

	@GetMapping("/register")
	public ModelAndView registerationPage() {
		final ModelAndView modelAndView = new ModelAndView();
		final UserDto loginUser = userService.getLoginUser().orElse(null);
		modelAndView.addObject("loginUser", loginUser);
		modelAndView.setViewName("community/register_board");
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detailPage(@PathVariable("id") Long id) {
		final ModelAndView modelAndView = new ModelAndView();
		final UserDto loginUser = userService.getLoginUser().orElse(null);
		final Board board = boardService.findById(id).orElseThrow(() -> new BoardNotFoundException());
		boardService.updateViewCount(board);
		modelAndView.addObject("loginUser", loginUser);
		modelAndView.addObject("board", board);
		modelAndView.setViewName("community/board_detail");
		return modelAndView;
	}
	
	@GetMapping("/download")
	public void downloadLink(@RequestParam("id") Long id, HttpServletResponse response) {
		final UserDto loginUser = userService.getLoginUser().orElse(null);
		final Board board = boardService.findById(id).orElseThrow(() -> new BoardNotFoundException());
		boardService.download(board, response);
	}
	
	@GetMapping("/update")
	public ModelAndView updatePage(@RequestParam("id") Long id) {
		final ModelAndView modelAndView = new ModelAndView();
		final UserDto loginUser = userService.getLoginUser().orElse(null);
		final Board board = boardService.findById(id).orElseThrow(() -> new BoardNotFoundException());
		modelAndView.addObject("loginUser", loginUser);
		modelAndView.addObject("board", board);
		modelAndView.setViewName("community/update_board");
		return modelAndView;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResultForm register(@Valid BoardRegistrationDto registrationDto) {
		final SimpleUser user = userService.findByEmail(registrationDto.getEmail())
				.orElseThrow(() -> new UserNotFoundException());
		final Board board = boardService.register(registrationDto, user);
		return ResultForm.of(board.getTitle() + " 게시물이 등록되었습니다.", 200, true);
	}

	@PostMapping("/update")
	@ResponseBody
	public ResultForm update(@Valid BoardUpdateDto updateDto) {
		final SimpleUser user = userService.findByEmail(updateDto.getEmail())
				.orElseThrow(() -> new UserNotFoundException());
		final Board board = boardService.update(updateDto, user);
		return ResultForm.of(board.getTitle() + " 게시물이 수정되었습니다.", 200, true);
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResultForm delete(@RequestParam("id") Long id, @RequestParam("email") String email) {
		final SimpleUser user = userService.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
		final Board board = boardService.findById(id).orElseThrow(() -> new RestBoardNotFoundException(id));
		final String title = board.getTitle();
		boardService.delete(board.getId());
		return ResultForm.of(title + " 게시물이 삭제되었습니다.", 200, true);
	}
}
