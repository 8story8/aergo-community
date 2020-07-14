package io.blocko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class HomeController {
	
	@GetMapping("/")
	public ModelAndView home() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("template/community/index");
		return modelAndView;
	}
}
