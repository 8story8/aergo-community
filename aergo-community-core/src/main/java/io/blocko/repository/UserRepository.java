package io.blocko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
