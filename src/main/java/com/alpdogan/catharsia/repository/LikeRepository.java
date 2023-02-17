package com.alpdogan.catharsia.repository;


import com.alpdogan.catharsia.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    Like findLikeById(int id);


}
