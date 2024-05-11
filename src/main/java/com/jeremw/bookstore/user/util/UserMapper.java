package com.jeremw.bookstore.user.util;

import java.util.List;

import com.jeremw.bookstore.user.User;
import com.jeremw.bookstore.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface UserMapper {

	/**
	 * Converts a User entity to a UserDto object.
	 *
	 * @param user The User entity to convert.
	 * @return The corresponding UserDto object.
	 */
	UserDto toDto(User user);

	/**
	 * Converts a list of User entities to a list of UserDto objects.
	 *
	 * @param users The list of User entities to convert.
	 * @return The corresponding list of UserDto objects.
	 */
	List<UserDto> toDtoList(List<User> users);
}