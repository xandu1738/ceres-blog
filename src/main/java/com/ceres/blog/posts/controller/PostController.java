package com.ceres.blog.posts.controller;

import com.ceres.blog.posts.model.Posts;
import com.ceres.blog.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostsService service;
    @GetMapping
    List<Posts> allPosts(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    Optional<Posts> single(@PathVariable Long id){
        return service.getOne(id);
    }
    @PostMapping
    Posts makePost(@RequestBody Posts post){
        return service.savePost(post);
    }
    @PutMapping
    Posts editPost(@RequestBody Posts post){
        return service.updatePost(post);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        service.removePost(id);
    }
}
