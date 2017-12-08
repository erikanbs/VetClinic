package com.ekholabs.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ekholabs.dto.UserDto;
import com.ekholabs.model.Role;
import com.ekholabs.model.User;
import com.ekholabs.service.RoleService;
import com.ekholabs.service.UserService;

@RestController
@RequestMapping(path = "/vet/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(path = "/users/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody @Validated UserDto user) {
		Role role = roleService.findOne(user.getRoleId());
		User newUser = new User(user.getFullName(), user.getEmail(), user.getFunction(), user.getDateIn(), role);
		userService.create(newUser);
	}

	@GetMapping(path = "/users/getAll")
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping(path = "/users/{userId}")
	public UserDto getUser(@PathVariable(value = "userId") int userId) {
		User user = userService.findOne(userId);
		// if (user == null) {
		// throw new NoSuchElementException("User does not exist: " + userId);
		// }
		return convertToDto(user);
	}

	@RequestMapping(path = "/users/{userId}", method = RequestMethod.PATCH)
	public UserDto updateUser(@PathVariable(value = "userId") int userId, @RequestBody @Validated UserDto userDto) {

		User user = verifyUser(userId);

		if (userDto.getEmail() != null) {
			user.setEmail(userDto.getEmail());
		}
		return convertToDto(userService.save(user));

	}

	@RequestMapping(path = "/users/{userId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "userId") int userId) {
		User user = verifyUser(userId);
		userService.delete(user);
	}

	private User verifyUser(int id) throws NoSuchElementException {
		User user = userService.findOne(id);
		if (user == null) {
			throw new NoSuchElementException("User does not exist: " + id);
		}
		return user;
	}

	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		Role role = user.getRole();
		if (role != null) {
			userDto.setRoleDescription(role.getDescription());
		}
		return userDto;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();

	}
}
