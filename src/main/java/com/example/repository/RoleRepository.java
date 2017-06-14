package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Role;

/**
 * The Interface RoleRepository.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	/**
	 * Find by role.
	 *
	 * @param role the role
	 * @return the role
	 */
	Role findByRole(String role);
}
