package com.jeremw.bookstore.user;

import java.util.List;

import com.jeremw.bookstore.user.dto.CreateUserForm;
import com.jeremw.bookstore.user.dto.UpdateUserForm;
import com.jeremw.bookstore.user.dto.UserDto;
import com.jeremw.bookstore.user.util.UserMapper;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestResponse;
/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Path("/api/users")
@ApplicationScoped
public class UserResource {
	private final UserMapper userMapper;

	private final UserService userService;

	public UserResource(UserMapper userMapper, UserService userService) {
		this.userMapper = userMapper;
		this.userService = userService;
	}

	@PermitAll
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get list of users", description = "Retrieves a list of users")
	@APIResponse(responseCode = "200", description = "List of users returned successfully")
	@APIResponse(responseCode = "401", description = "Unauthorized")
	@APIResponse(responseCode = "500", description = "Internal server error")
	public Uni<RestResponse<List<UserDto>>> getUsers() {
		Log.infof("Fetching users.");
		return userService.getAllUsers()
				.map(userMapper::toDtoList)
				.map(RestResponse::ok);
	}

	/**
	 * Retrieves a user by ID.
	 *
	 * @param id The ID of the user to retrieve
	 * @return A Uni emitting a RestResponse containing the user DTO
	 */
	@PermitAll
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get user by ID", description = "Retrieves a user by its ID")
	public Uni<RestResponse<UserDto>> getUserById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
		return userService.getUserById(id)
				.map(userMapper::toDto)
				.map(RestResponse::ok);
	}


	/**
	 * Creates a new user.
	 *
	 * @param createUserForm The form containing user data
	 * @return A Uni emitting a RestResponse containing the created user DTO
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Create user", description = "Creates a new user")
	public Uni<RestResponse<UserDto>> createUser(@Valid @NotNull CreateUserForm createUserForm) {
		return userService.createUser(createUserForm)
				.map(userMapper::toDto)
				.map(user -> RestResponse.ResponseBuilder.ok(user).build());
	}

	@PermitAll
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Update user by ID", description = "Update a user by its ID")
	public Uni<RestResponse<UserDto>> updateUserById(@Parameter(name = "id", required = true) @PathParam("id") Long id, UpdateUserForm updateUserForm) {
		return userService.updateUserById(id, updateUserForm)
				.map(userMapper::toDto)
				.map(user -> RestResponse.ResponseBuilder.ok(user).build());
	}

	@PermitAll
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Delete user by ID", description = "Delete a user by its ID")
	public Uni<RestResponse<Void>> deleteUserById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
		return userService.deleteUserById(id).onItem().transform(x -> RestResponse.noContent());
	}
}