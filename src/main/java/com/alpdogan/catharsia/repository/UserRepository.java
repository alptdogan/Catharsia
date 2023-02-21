package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

}
