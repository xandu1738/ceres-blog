package com.ceres.blog.likes.controller;

import com.ceres.blog.likes.model.Likes;
import com.ceres.blog.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class LikeController {
    private final LikeService service;
    @GetMapping
    List<Likes> allLikes(){
        return service.getAll();
    }
    @PostMapping
    Likes makeLike(@RequestBody Likes like){
        return service.saveLike(like);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        service.removeLike(id);
    }
}
