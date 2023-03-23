package com.mohit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
