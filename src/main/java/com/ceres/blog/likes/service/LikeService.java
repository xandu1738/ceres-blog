package com.ceres.blog.likes.service;

import com.ceres.blog.likes.model.Likes;
import com.ceres.blog.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService implements ILikeService{
    private final LikesRepository repository;
    @Override
    public List<Likes> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Likes> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Likes saveLike(Likes like) {
        return repository.save(like);
    }

    @Override
    public Likes updateLike(Likes like) {
        return repository.save(like);
    }

    @Override
    public String removeLike(Long id) {
        repository.deleteById(id);
        return "Deleted";
    }
}
