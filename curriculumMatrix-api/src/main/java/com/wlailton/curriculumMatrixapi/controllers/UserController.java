package com.wlailton.curriculumMatrixapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('ADMIN')")
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
	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

	}

	/**
	 * Update a user.
	 */
	@PutMapping("/{username}")
	public User updateUserByUsername(@PathVariable String username, @Valid @RequestBody User userUpdated) {
		User userOld = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		userUpdated.setId(userOld.getId());
		return userRepository.save(userUpdated);
	}

	/**
	 * Delete a user.
	 */
	@DeleteMapping("/{username}")
	public void deleteUserByUsername(@PathVariable String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		userRepository.deleteById(user.getId());

	}

}
