package io.blocko.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardUpdateDto {
	
	Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private MultipartFile file;
	
	@Email
	private String email;
	
	private String hasAlreadyFile;
	
	private String uploadStatus;
}
