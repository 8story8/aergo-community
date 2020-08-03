package io.blocko.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.dto.UserLoginDto;
import io.blocko.dto.UserRegistrationDto;
import io.blocko.form.ResultForm;
import io.blocko.model.User;
import io.blocko.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@ModelAttribute final UserLoginDto loginDto) {
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public ResultForm logout() {
		return null;
	}
	
	@GetMapping("/register") 
	public ModelAndView registrationPage() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("registrationDto", new UserRegistrationDto());
		modelAndView.setViewName("community/register");
		return modelAndView;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResultForm register(@Valid @ModelAttribute UserRegistrationDto registrationDto){
		final User user = userService.register(registrationDto);
		return ResultForm.of(user, 100, true);
	}
}
