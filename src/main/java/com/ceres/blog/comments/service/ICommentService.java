package com.ceres.blog.comments.service;

import com.ceres.blog.comments.model.Comments;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comments> getAll();
    Optional<Comments> getOne(Long id);
    Comments saveComment(Comments comment);
    Comments updateComment(Comments comment);
    String removeComment(Long id);
}
