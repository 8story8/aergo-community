package io.blocko.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.blocko.dto.UserLoginDto;
import io.blocko.exception.UserLoginValidationException;

public class UsernamePasswordValidationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private Validator validator;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		final UserLoginDto loginDto = new UserLoginDto();
		loginDto.setEmail(request.getParameter("email"));
		loginDto.setPassword(request.getParameter("password"));
		
		System.out.println("1111111111111111");
		System.out.println(request.getParameter("email"));
		System.out.println("111111111111111");
		
		final Errors errors = new BeanPropertyBindingResult(loginDto, "loginDto");
		
		validator.validate(loginDto, errors);
		
		if(errors.hasErrors()) {
			throw new UserLoginValidationException("1111");
		}
		
		return super.attemptAuthentication(request, response);
	}
}
