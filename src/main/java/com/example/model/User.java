package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class User.
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	/** The email. */
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	/** The password. */
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	
	/** The first name. */
	@Column(name = "first_name")
	@NotEmpty(message = "*Please provide your first name")
	private String firstName;
	
	/** The last name. */
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	/** The address. */
	@Column(name = "address")
	@NotEmpty(message = "*Please provide your address")
	private String address ;
	
	/** The city. */
	@Column(name = "city")
	@NotEmpty(message = "*Please provide your city")
	private String city;
	
	/** The country. */
	@Column(name = "country")
	@NotEmpty(message = "*Please provide your country")
	private String country;
	
	/** The active. */
	@Column(name = "active")
	private int active;
	
	/** The roles. */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
