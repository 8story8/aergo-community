package io.blocko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findOneByEmail(String email);
	
	boolean existsByEmail(String email);
}
