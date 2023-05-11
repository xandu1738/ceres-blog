package com.ceres.blog.comments.service;

import com.ceres.blog.comments.model.Comments;
import com.ceres.blog.comments.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final CommentsRepository repository;
    @Override
    public List<Comments> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comments> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Comments saveComment(Comments comment) {
        return repository.save(comment);
    }

    @Override
    public Comments updateComment(Comments comment) {
        return repository.save(comment);
    }

    @Override
    public String removeComment(Long id) {
        repository.deleteById(id);
        return "Comment Deleted";
    }
}
