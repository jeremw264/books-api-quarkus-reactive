package com.jeremw.bookstore.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Data
public class CreateBookForm {
	@NotBlank(message = "Title is required.")
	private String title;
	@NotBlank(message = "Author is required.")
	private String author;
}