package com.ceres.blog.likes.service;

import com.ceres.blog.likes.model.Likes;

import java.util.List;
import java.util.Optional;

public interface ILikeService {
    List<Likes> getAll();
    Optional<Likes> getOne(Long id);
    Likes saveLike(Likes like);
    Likes updateLike(Likes like);
    String removeLike(Long id);
}
