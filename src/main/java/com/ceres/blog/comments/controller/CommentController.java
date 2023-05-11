package com.ceres.blog.comments.controller;

import com.ceres.blog.comments.model.Comments;
import com.ceres.blog.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @GetMapping
    List<Comments> allComments(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    Optional<Comments> single(@PathVariable Long id){
        return service.getOne(id);
    }
    @PostMapping
    Comments saveComm(@RequestBody Comments comment){
        return service.saveComment(comment);
    }
    @PutMapping
    Comments updateComm(@RequestBody Comments comment){
        return service.updateComment(comment);
    }
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id){
        service.removeComment(id);
    }
}
