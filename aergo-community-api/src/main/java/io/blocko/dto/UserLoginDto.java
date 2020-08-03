package io.blocko.dto;

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

	@NotNull
	private String email;
	
	@NotNull
	private String password;
}
