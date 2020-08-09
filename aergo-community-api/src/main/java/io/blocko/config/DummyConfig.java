package io.blocko.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import io.blocko.dto.BoardRegistrationDto;
import io.blocko.dto.UserRegistrationDto;
import io.blocko.model.SimpleUser;
import io.blocko.service.BoardService;
import io.blocko.service.UserService;

@Configuration
public class DummyConfig {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;

//	@PostConstruct
//	public void setUpDummy() {
//		final SimpleUser dummyUser = registerDummyUser();
//		registerDummyBoard(dummyUser);
//	}
	
	public SimpleUser registerDummyUser() {
		final UserRegistrationDto registrationDto = new UserRegistrationDto();
		registrationDto.setEmail("8story8@blocko.io");
		registrationDto.setPassword("1234");
		registrationDto.setConfirmedPassword("1234");
		registrationDto.setName("8story8");
		return userService.register(registrationDto);
	}
	
	public void registerDummyBoard(SimpleUser user) {
		for(int i = 0; i < 125; i++) {
			final BoardRegistrationDto registrationDto = new BoardRegistrationDto();
			final int index = i+1;
			registrationDto.setTitle("제목 " + index);
			registrationDto.setContent("내용 " + index);
			registrationDto.setFile(new MultipartFile() {
				
				@Override
				public void transferTo(File dest) throws IOException, IllegalStateException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public boolean isEmpty() {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public long getSize() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getOriginalFilename() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getName() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public InputStream getInputStream() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getContentType() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public byte[] getBytes() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
			});
			boardService.register(registrationDto, user);	
		}
	}
}
