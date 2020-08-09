package io.blocko.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.blocko.dto.UserDto;
import io.blocko.dto.UserRegistrationDto;
import io.blocko.exception.UserDuplicationException;
import io.blocko.exception.UserPasswordNotEqualsException;
import io.blocko.model.SimpleUser;
import io.blocko.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findOneByEmail(email)
				.map(user -> new User(user.getEmail(), user.getPassword(), new HashSet<GrantedAuthority>()))
				.orElseThrow(() -> new UsernameNotFoundException(email));
	}

	public Optional<UserDto> getLoginUser() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(Authentication::getPrincipal).filter(o -> o instanceof UserDetails).map(o -> (UserDetails) o)
				.map(o -> userRepository.findOneByEmail(o.getUsername())
						.orElseThrow(() -> new UsernameNotFoundException(o.getUsername())))
				.map(o -> new UserDto(o.getId(), o.getEmail(), o.getName()));
	}

	public SimpleUser register(UserRegistrationDto registrationDto) {
		final String email = registrationDto.getEmail();
		final String password = registrationDto.getPassword();
		final String name = registrationDto.getName();

		if (!password.equals(registrationDto.getConfirmedPassword())) {
			throw new UserPasswordNotEqualsException();
		}

		if (userRepository.existsByEmail(email)) {
			throw new UserDuplicationException(email);
		}

		final SimpleUser user = userRepository.save(new SimpleUser(email, passwordEncoder.encode(password), name));
		return user;
	}

	public Optional<SimpleUser> findByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

}
