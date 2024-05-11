package com.jeremw.bookstore.book;

import java.util.List;

import com.jeremw.bookstore.book.dto.CreateBookForm;
import com.jeremw.bookstore.book.dto.UpdateBookForm;
import com.jeremw.bookstore.exception.ResourceException;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@ApplicationScoped
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@WithSpan("BookService.getAllBooks")
	public Uni<List<Book>> getAllBooks() {
		Log.debug("Get all Books");
		return bookRepository.listAll();
	}

	@WithSpan("BookService.getBookById")
	public Uni<Book> getBookById(final Long bookId) {
		Log.debugf("Get book by ID : %s", bookId);
		return bookRepository.findById(bookId).onItem().ifNull()
				.failWith(() -> new ResourceException("BookNotFound", "Book not found with id: " + bookId, Status.NOT_FOUND));
	}

	@WithSpan("BookService.createBook")
	@WithTransaction
	public Uni<Book> createBook(final CreateBookForm createBookForm) {
		Log.debug("Create a new book.");
		return Uni.createFrom().item(() -> Book.builder()
						.title(createBookForm.getTitle())
						.author(createBookForm.getAuthor())
						.build()
				).onItem().transformToUni(book -> bookRepository.persist(book))
				.onItem().ifNull()
				.failWith(() -> new ResourceException("CreateBookError", "Error while creating book.", Status.INTERNAL_SERVER_ERROR));
	}
	@WithSpan("BookService.updateBookById")
	@WithTransaction
	public Uni<Book> updateBookById(final Long bookId, final UpdateBookForm updateBookForm) {
		Log.debugf("Update book by ID : %s",bookId);
		return getBookById(bookId).onItem().transformToUni(book -> {
			if (updateBookForm.getTitle() != null)
				book.setTitle(updateBookForm.getTitle());
			if (updateBookForm.getAuthor() != null)
				book.setAuthor(updateBookForm.getAuthor());
			return bookRepository.persist(book);
		});
	}
	@WithSpan("BookService.deleteBookById")
	@WithTransaction
	public Uni<Void> deleteBookById(final Long bookId) {
		Log.debugf("Delete book by ID : %s",bookId);
		return getBookById(bookId).onItem().transformToUni(book -> bookRepository.delete(book));

	}

}
