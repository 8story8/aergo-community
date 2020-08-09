package io.blocko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.dto.UserDto;
import io.blocko.model.Board;
import io.blocko.service.BoardService;
import io.blocko.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService; 

	@GetMapping("/")
	public ModelAndView indexPage() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/index");
		return modelAndView;
	}

	@GetMapping("/main")
	public ModelAndView mainPage(Pageable pageable) {
		final ModelAndView modelAndView = new ModelAndView();
		final UserDto loginUser = userService.getLoginUser().orElse(null);
		final Page<Board> boardPages = boardService.findAll(pageable);
		modelAndView.addObject("loginUser", loginUser);
		modelAndView.addObject("boardPages", boardPages);
		modelAndView.setViewName("community/main");
		return modelAndView;
	}
}
