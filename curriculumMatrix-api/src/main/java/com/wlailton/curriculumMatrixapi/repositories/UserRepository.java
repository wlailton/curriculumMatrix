package com.wlailton.curriculumMatrixapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wlailton.curriculumMatrixapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
