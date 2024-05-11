package com.jeremw.bookstore.book;

import java.util.List;

import com.jeremw.bookstore.book.dto.CreateBookForm;
import com.jeremw.bookstore.book.dto.UpdateBookForm;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Path("/api/books")
@Tag(name = "Books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

	private final BookService bookService;

	public BookResource(BookService bookService) {
		this.bookService = bookService;
	}

	@GET
	public Uni<RestResponse<List<Book>>> getAllBooks() {
		return bookService.getAllBooks().map(books -> RestResponse.status(Status.OK, books));
	}

	@GET
	@Path("/{bookId}")
	public Uni<RestResponse<Book>> getBookById(@PathParam("bookId") Long bookId) {
		return bookService.getBookById(bookId).map(book -> RestResponse.status(Status.OK, book));
	}

	@POST
	public Uni<RestResponse<Book>> createBook(@Valid CreateBookForm createBookForm) {
		return bookService.createBook(createBookForm).map(book -> RestResponse.status(Status.CREATED, book));
	}

	@PATCH
	@Path("/{bookId}")
	public Uni<RestResponse<Book>> updateBookById(@PathParam("bookId") Long bookId, UpdateBookForm updateBookForm) {
		return bookService.updateBookById(bookId, updateBookForm).map(book -> RestResponse.status(Status.OK, book));
	}

	@DELETE
	@Path("/{bookId}")
	public Uni<RestResponse<Void>> deleteBookById(@PathParam("bookId") Long bookId) {
		return bookService.deleteBookById(bookId).map(book -> RestResponse.status(Status.NO_CONTENT, book));
	}

}