package io.blocko.dto;

import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationDto {

	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String confirmedPassword;
	
	@NotNull
	private String name;
	
}
