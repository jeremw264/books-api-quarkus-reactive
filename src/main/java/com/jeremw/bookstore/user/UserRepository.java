package com.jeremw.bookstore.user;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@ApplicationScoped
@WithSession
public class UserRepository implements PanacheRepository<User> {

	public Uni<User> findByUsername(String username) {
		return find("username", username).firstResult();
	}

}