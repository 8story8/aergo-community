package io.blocko.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.dto.BoardRegistrationDto;
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
	public ModelAndView register() {
		final ModelAndView modelAndView = new ModelAndView();
		final SimpleUser loginUser = userService.getLoginUser().orElse(null);
		modelAndView.addObject("name", loginUser.getName());
		modelAndView.setViewName("community/register_board");
		return modelAndView;
	}

	@PostMapping("/register")
	@ResponseBody
	public ResultForm register(@Valid BoardRegistrationDto registrationDto) {
		final SimpleUser loginUser = userService.getLoginUser().orElse(null);

		final Board board = boardService.register(loginUser, registrationDto);

		return ResultForm.of("", 200, true);
	}
}
