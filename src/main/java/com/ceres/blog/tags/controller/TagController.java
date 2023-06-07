package com.ceres.blog.tags.controller;

import com.ceres.blog.tags.model.PostTag;
import com.ceres.blog.tags.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class TagController {
    private final TagService service;
    @GetMapping("/list")
    List<PostTag> allTags(){
        return service.tags();
    }
    @GetMapping("/{id}")
    Optional<PostTag> singleTag(@PathVariable Long id){
        return service.singleTag(id);
    }
    @PostMapping("/save")
    PostTag saveTag(@RequestBody PostTag tag){
        return service.saveTag(tag);
    }
    @PutMapping("/update")
    PostTag updateTag(@RequestBody PostTag tag){
        return service.updateTag(tag);
    }
    @DeleteMapping("/{id}")
    void removeTag(@PathVariable Long id){
        service.removeTag(id);
    }
}
