package com.jeremw.bookstore.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {

	@Schema(description = "Id of the updated password")
	private Long id;

	@Schema(description = "Email of the updated password")
	private String email;

	@Schema(description = "Password of the updated password")
	private String password;

}
