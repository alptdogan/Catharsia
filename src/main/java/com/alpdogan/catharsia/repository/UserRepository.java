package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);

}
