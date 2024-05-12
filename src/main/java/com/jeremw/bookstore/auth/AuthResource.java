package com.jeremw.bookstore.auth;

import com.jeremw.bookstore.auth.dto.AuthDto;
import com.jeremw.bookstore.auth.dto.LoginForm;
import com.jeremw.bookstore.auth.dto.RegisterForm;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Path("api/auth")
public class AuthResource {

	private final AuthService authService;

	public AuthResource(AuthService authService) {
		this.authService = authService;
	}

	@Path("/login")
	@POST
	public Uni<RestResponse<AuthDto>> login(final LoginForm loginForm) {
		return authService.login(loginForm).map(RestResponse::ok);
	}

	@Path("/register")
	@POST
	public Uni<RestResponse<AuthDto>> login(final RegisterForm registerForm) {
		return authService.register(registerForm).map(RestResponse::ok);
	}

}
