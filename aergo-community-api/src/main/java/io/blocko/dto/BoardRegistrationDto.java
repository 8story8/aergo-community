package io.blocko.dto;

import java.sql.Blob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardRegistrationDto {

	private String title;
	
	private String content;
	
	private Blob photo;
}
