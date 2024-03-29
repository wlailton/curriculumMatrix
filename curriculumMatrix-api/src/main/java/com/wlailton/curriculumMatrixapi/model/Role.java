package com.wlailton.curriculumMatrixapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import org.hibernate.annotations.NaturalId;

import com.wlailton.curriculumMatrixapi.enums.RoleNameEnum;
 
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleNameEnum name;
 
    public Role() {}
 
    public Role(RoleNameEnum name) {
        this.name = name;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public RoleNameEnum getName() {
        return name;
    }
 
    public void setName(RoleNameEnum name) {
        this.name = name;
    }
}