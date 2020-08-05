package io.blocko.dto;

import java.sql.Blob;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardRegistrationDto {

	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private Blob file;
}
