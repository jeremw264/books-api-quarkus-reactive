package com.jeremw.bookstore.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
	private String username;
	private String password;
}
