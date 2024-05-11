package com.jeremw.bookstore.user.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO representing the data required to create a new user.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {

	/**
	 * The username of the new user.
	 */
	@Schema(description = "Username of the new user", required = true)
	@NotBlank(message = "Username is required")
	private String username;

	/**
	 * The email address of the new user.
	 */
	@Schema(description = "Email address of the new user", required = true)
	@NotBlank(message = "Invalid email format")
	private String email;

	/**
	 * The password of the new user.
	 */
	@Schema(description = "Password of the new user", required = true)
	@NotBlank(message = "Password is required")
	private String password;
}
