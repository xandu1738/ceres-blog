package com.ceres.blog.posts.service;

import com.ceres.blog.posts.model.Posts;

import java.util.List;
import java.util.Optional;

public interface IPostsService {
    List<Posts> getAll();
    Optional<Posts> getOne(Long id);
    Posts savePost(Posts post);
    Posts updatePost(Posts post);
    String removePost(Long id);
}
