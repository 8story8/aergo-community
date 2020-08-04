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
import io.blocko.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/register")
	public ModelAndView register() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/register_board");
		return modelAndView;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResultForm register(@Valid BoardRegistrationDto registrationDto){
		return ResultForm.of(registrationDto.getTitle() + " 글이 작성되었습니다.", 200, true);
	}
}
