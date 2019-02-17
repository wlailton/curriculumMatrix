package com.wlailton.curriculumMatrixapi.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlailton.curriculumMatrixapi.enums.RoleNameEnum;
import com.wlailton.curriculumMatrixapi.exception.UserNotFoundException;
import com.wlailton.curriculumMatrixapi.model.Role;
import com.wlailton.curriculumMatrixapi.model.User;
import com.wlailton.curriculumMatrixapi.repositories.RoleRepository;
import com.wlailton.curriculumMatrixapi.repositories.UserRepository;
import com.wlailton.curriculumMatrixapi.security.JwtResponse;
import com.wlailton.curriculumMatrixapi.security.LoginForm;
import com.wlailton.curriculumMatrixapi.security.SignUpForm;
import com.wlailton.curriculumMatrixapi.security.jwt.JwtProvider;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    @Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserRepository userRepository;
 
    @Autowired
    RoleRepository roleRepository;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
    
    /**
	 * Get a user.
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUser(@PathVariable String id) {
		return userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UserNotFoundException(id));

	}

	/**
	 * User signin.
	 */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
 
    /**
	 * User signin.
	 */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
 
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
 
        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
 
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
 
        strRoles.forEach(role -> {
        	switch(role) {
	    		case "admin":
	    			Role adminRole = roleRepository.findByName(RoleNameEnum.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Cause: User Role not find."));
	    			roles.add(adminRole);
	    			
	    			break;
	    		case "coordinator":
	            	Role coordinatorRole = roleRepository.findByName(RoleNameEnum.ROLE_COORDINATOR)
	                .orElseThrow(() -> new RuntimeException("Cause: User Role not find."));
	            	roles.add(coordinatorRole);
	            	
	    			break;
	    		case "professor":
	            	Role professorRole = roleRepository.findByName(RoleNameEnum.ROLE_PROFESSOR)
	                .orElseThrow(() -> new RuntimeException("Cause: User Role not find."));
	            	roles.add(professorRole);
	            	
	    			break;
	    		default:
	        		Role studentRole = roleRepository.findByName(RoleNameEnum.ROLE_STUDENT)
	                .orElseThrow(() -> new RuntimeException("Cause: User Role not find."));
	        		roles.add(studentRole);        			
        	}
        });
        
        user.setRoles(roles);
        userRepository.save(user);
 
        return ResponseEntity.ok().body("User registered successfully!");
    }
}