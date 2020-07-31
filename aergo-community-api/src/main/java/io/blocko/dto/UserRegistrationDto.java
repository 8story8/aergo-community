package io.blocko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRegistrationDto {

	private String email;
	
	private String password;
	
	private String name;
	
	private String confirmedPassword;
}
