package io.blocko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.dto.UserLoginDto;
import io.blocko.model.SimpleUser;
import io.blocko.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ModelAndView indexPage() {
		final ModelAndView modelAndView = new ModelAndView();
		 final SimpleUser loginUser = userService.getLoginUser().orElse(null);
		 if(loginUser == null) {
			 modelAndView.addObject("loginDto", new UserLoginDto());
			 modelAndView.setViewName("community/index");
		 }else {
			 modelAndView.setViewName("community/main");
		 }
		return modelAndView;
	}
}
