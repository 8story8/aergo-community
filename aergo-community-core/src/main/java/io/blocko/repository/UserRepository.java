package io.blocko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.SimpleUser;

public interface UserRepository extends JpaRepository<SimpleUser, Long>{
	
	Optional<SimpleUser> findOneByEmail(String email);
	
	boolean existsByEmail(String email);
}
