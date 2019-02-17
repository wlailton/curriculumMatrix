package com.wlailton.curriculumMatrixapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlailton.curriculumMatrixapi.enums.RoleNameEnum;
import com.wlailton.curriculumMatrixapi.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleNameEnum roleName);
}