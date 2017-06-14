package com.example.service;

import com.example.model.User;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findUserByEmail(String email);

	/**
	 * Save user.
	 *
	 * @param user the user
	 */
	public void saveUser(User user);
}
