package com.jeremw.bookstore.book.dto;

import lombok.Data;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Data
public class UpdateBookForm {
	private String title;
	private String author;
}