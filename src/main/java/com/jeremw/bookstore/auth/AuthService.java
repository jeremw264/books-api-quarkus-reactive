package com.jeremw.bookstore.auth;

import com.jeremw.bookstore.auth.dto.AuthDto;
import com.jeremw.bookstore.auth.dto.LoginForm;
import com.jeremw.bookstore.auth.dto.RegisterForm;
import com.jeremw.bookstore.exception.ResourceException;
import com.jeremw.bookstore.user.UserService;
import com.jeremw.bookstore.user.dto.CreateUserForm;
import com.jeremw.bookstore.user.dto.UserDto;
import com.jeremw.bookstore.user.util.UserMapper;
import com.jeremw.bookstore.util.PBKDF2Encoder;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 12/05/2024
 */
@ApplicationScoped
public class AuthService {

	@Inject
	PBKDF2Encoder passwordEncoder;

	@Inject
	JwtService jwtService;

	private final UserService userService;
	private final UserMapper userMapper;

	public AuthService(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	public Uni<AuthDto> login(final LoginForm loginForm){
		return this.userService.getUserByUsername(loginForm.getUsername()).map(Unchecked.function(user -> {
				if (!user.getPassword().equals(passwordEncoder.encode(loginForm.getPassword()))) {
					throw new ResourceException("BadCredential", "Bad Credential", Status.UNAUTHORIZED);
				}
				String token = jwtService.generateToken(user);
				UserDto userDto = userMapper.toDto(user);
				return AuthDto.builder()
						.user(userDto)
						.token(token)
						.build();
		}));
	}

	public Uni<AuthDto> register(final RegisterForm registerForm){

		CreateUserForm createUserForm = CreateUserForm.builder()
				.username(registerForm.getUsername())
				.email(registerForm.getEmail())
				.password(registerForm.getPassword())
				.build();

		return userService.createUser(createUserForm).map(user -> {
			String token = jwtService.generateToken(user);
			UserDto userDto = userMapper.toDto(user);
			return AuthDto.builder()
					.user(userDto)
					.token(token)
					.build();
		});
	}

}
