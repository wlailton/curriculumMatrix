package com.wlailton.curriculumMatrixapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "year", "semesterNumber", "course_id" }) })
public class Semester {

	@Id
	@GeneratedValue
	private Long id;

	@GeneratedValue
	@Digits(integer = 4, fraction = 0)
	private Long year;

	@NotNull
	@Min(1)
	@Max(4)
	private int semesterNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private Course course;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "semester_discipline", 
		joinColumns = { @JoinColumn(name = "semester_id", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "discipline_id") })
	private Set<Discipline> disciplines = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public int getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

}
