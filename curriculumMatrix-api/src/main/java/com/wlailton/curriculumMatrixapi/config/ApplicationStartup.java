package com.wlailton.curriculumMatrixapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wlailton.curriculumMatrixapi.repositories.RoleRepository;
import com.wlailton.curriculumMatrixapi.repositories.UserRepository;

@Component
public class ApplicationStartup implements ApplicationRunner {

	@Autowired
	private PasswordEncoder encoder;

	private UserRepository userRepository;

	@Autowired
	public ApplicationStartup(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Set up the test user password.
		userRepository.findByUsername("usertest").ifPresent(user -> {
			user.setPassword(encoder.encode("123456"));
			userRepository.save(user);
		});
	}
}
