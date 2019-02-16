package com.wlailton.curriculumMatrixapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 3, max = 250)
	private String name;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Semester> semesters = new HashSet<>();

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

}
