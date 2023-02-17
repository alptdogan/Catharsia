package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.entity.Like;
import com.alpdogan.catharsia.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public void createLike(Like therapist) {
        likeRepository.save(therapist);
    }

    public void updateLikeById(int id, Like therapist) {
        therapist.setId(id);
        likeRepository.save(therapist);
    }

    public void deleteLikeById(int id) {
        likeRepository.delete(likeRepository.findLikeById(id));
    }

}
