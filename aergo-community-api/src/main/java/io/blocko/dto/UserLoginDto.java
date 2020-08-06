package io.blocko.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	private String password;
}
