package io.blocko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.blocko.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
}