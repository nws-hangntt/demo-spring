/*
 * 
 */
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

/**
 * The Interface UserRepository.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findByEmail(String email);
}
