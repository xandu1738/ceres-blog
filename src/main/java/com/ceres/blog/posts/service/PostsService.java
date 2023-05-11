package com.ceres.blog.posts.service;

import com.ceres.blog.posts.model.Posts;
import com.ceres.blog.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService{
    private final PostsRepository repository;
    @Override
    public List<Posts> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Posts> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Posts savePost(Posts post) {
        return repository.save(post);
    }

    @Override
    public Posts updatePost(Posts post) {
        return repository.save(post);
    }

    @Override
    public String removePost(Long id) {
        repository.deleteById(id);
        return "Item removed.";
    }
}
