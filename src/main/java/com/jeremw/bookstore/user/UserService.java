package com.jeremw.bookstore.user;

import java.util.List;

import com.jeremw.bookstore.exception.ResourceException;
import com.jeremw.bookstore.user.dto.CreateUserForm;
import com.jeremw.bookstore.user.dto.UpdateUserForm;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@ApplicationScoped
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Uni<List<User>> getAllUsers() {
		Log.debug("Get all Users");
		return userRepository.listAll();
	}

	public Uni<User> getUserById(final Long userId) {
		Log.debugf("Get user by ID : %s", userId);
		return userRepository.findById(userId).onItem().ifNull()
				.failWith(() -> new ResourceException("UserNotFound", "User not found with id: " + userId, Response.Status.NOT_FOUND));
	}

	@WithTransaction
	public Uni<User> createUser(final CreateUserForm createUserForm) {
		Log.debug("Create a new user.");
		return Uni.createFrom().item(() -> User.builder()
						.username(createUserForm.getUsername())
						.email(createUserForm.getEmail())
						.build()
				).onItem().transformToUni(user -> userRepository.persist(user))
				.onItem().ifNull()
				.failWith(() -> new ResourceException("CreateUserError", "Error while creating user.", Response.Status.INTERNAL_SERVER_ERROR));
	}

	@WithTransaction
	public Uni<User> updateUserById(final Long userId, final UpdateUserForm updateUserForm) {
		Log.debugf("Update user by ID : %s", userId);
		return getUserById(userId).onItem().transformToUni(user -> {
			if (updateUserForm.getEmail() != null)
				user.setEmail(updateUserForm.getEmail());
			if (updateUserForm.getPassword() != null) {
				user.setPassword(updateUserForm.getPassword());
			}
			return userRepository.persist(user);
		});
	}

	@WithTransaction
	public Uni<Void> deleteUserById(final Long userId) {
		Log.debugf("Delete user by ID : %s", userId);
		return getUserById(userId).onItem().transformToUni(user -> userRepository.delete(user));

	}

}