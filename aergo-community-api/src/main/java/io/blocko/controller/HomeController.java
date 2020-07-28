package io.blocko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView home() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/index");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/login");
		return modelAndView;
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/register");
		return modelAndView;
	}
	
	@PostMapping("/logout")
	public ModelAndView logout() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/login");
		return modelAndView;
	}
}
