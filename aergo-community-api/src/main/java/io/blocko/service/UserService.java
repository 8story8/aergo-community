package io.blocko.service;

import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.blocko.dto.UserRegistrationDto;
import io.blocko.exception.UserDuplicationException;
import io.blocko.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findOneByEmail(email)
				.map(user -> new User(user.getEmail(), user.getPassword(), new HashSet<GrantedAuthority>()))
				.orElseThrow(() -> new UsernameNotFoundException(email));
	}

	public Optional<io.blocko.model.User> getLoginUser() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(Authentication::getPrincipal).filter(o -> o instanceof UserDetails).map(o -> (UserDetails) o)
				.map(o -> userRepository.findOneByEmail(o.getUsername()).orElse(null));
	}

	public io.blocko.model.User register(UserRegistrationDto registrationDto) {
		final String email = registrationDto.getEmail();
		final String password = registrationDto.getPassword();
		final String name = registrationDto.getName();

		if (userRepository.existsByEmail(email)) {
			throw new UserDuplicationException(email);
		}

		io.blocko.model.User user = userRepository.save(new io.blocko.model.User(email, password, name));
		return user;
	}
}
