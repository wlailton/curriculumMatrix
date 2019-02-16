package com.wlailton.curriculumMatrixapi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wlailton.curriculumMatrixapi.enums.UserTypeEnum;

@Entity
@JsonInclude(Include.NON_NULL)
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 3, max = 150)
	private String name;

	@NotNull
	@Size(min = 3, max = 8)
	private String password;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserTypeEnum userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	}
