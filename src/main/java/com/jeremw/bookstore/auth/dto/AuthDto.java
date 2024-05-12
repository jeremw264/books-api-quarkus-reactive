package com.jeremw.bookstore.auth.dto;

import com.jeremw.bookstore.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 12/05/2024
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

	private UserDto user;
	private String token;

}
