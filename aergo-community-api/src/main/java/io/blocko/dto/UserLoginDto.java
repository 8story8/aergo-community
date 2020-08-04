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
public class UserLoginDto {

	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String password;
}
