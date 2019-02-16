package com.wlailton.curriculumMatrixapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlailton.curriculumMatrixapi.exception.UserNotFoundException;
import com.wlailton.curriculumMatrixapi.model.User;
import com.wlailton.curriculumMatrixapi.repositories.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Create a new user.
	 */
	@PostMapping("/")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	 * Get a user.
	 */
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UserNotFoundException(id));

	}

	/**
	 * Update a user.
	 */
	@PutMapping("/{id}")
	public User updateUser(@PathVariable String id, @Valid @RequestBody User userUpdated) {
		userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UserNotFoundException(id));
		userUpdated.setId(Long.parseLong(id));
		return userRepository.save(userUpdated);
	}

	/**
	 * Delete a user.
	 */
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UserNotFoundException(id));
		userRepository.deleteById(Long.parseLong(id));

	}

}
