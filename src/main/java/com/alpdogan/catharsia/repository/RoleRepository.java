package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.ERole;
import com.alpdogan.catharsia.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<Role> findByName(ERole name);

}











