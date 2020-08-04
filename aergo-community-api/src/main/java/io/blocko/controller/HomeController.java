package io.blocko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ModelAndView indexPage() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/main");
		return modelAndView;
	}
}
