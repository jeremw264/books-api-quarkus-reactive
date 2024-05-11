package com.jeremw.bookstore.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Data
@Table(name = "user_app")
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	/**
	 * Represents the unique identifier of a user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Represents the unique username of a user.
	 */
	@Column(unique = true,updatable = false)
	private String username;

	/**
	 * Represents the email address of a user.
	 */
	private String email;

	/**
	 * Represents the password of a user.
	 */
	private String password;

}