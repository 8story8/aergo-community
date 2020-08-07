package io.blocko.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.blocko.dto.UserRegistrationDto;
import io.blocko.form.ResultForm;
import io.blocko.model.SimpleUser;
import io.blocko.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register") 
	public ModelAndView registrationPage() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("community/register_user");
		return modelAndView;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResultForm register(@Valid UserRegistrationDto registrationDto){
		final SimpleUser user = userService.register(registrationDto);
		return ResultForm.of(user.getEmail() + "님이 가입했습니다.", 200, true);
	}
}
