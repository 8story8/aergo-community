package io.blocko.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {

	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String confirmedPassword;
	
	@NotBlank
	private String name;
	
}
