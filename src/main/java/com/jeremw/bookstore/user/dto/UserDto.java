package com.jeremw.bookstore.user.dto;

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
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

	/**
	 * The ID of the user.
	 */
	private Long id;

	/**
	 * The username of the user.
	 */
	private String username;

	/**
	 * The email of the user.
	 */
	private String email;
}