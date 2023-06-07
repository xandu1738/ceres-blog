package com.ceres.blog.tags.service;

import com.ceres.blog.tags.model.PostTag;
import com.ceres.blog.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository repository;
    public List<PostTag> tags(){
        return repository.findAll();
    }
    public Optional<PostTag> singleTag(Long id){
        return repository.findById(id);
    }
    public PostTag saveTag(PostTag tag){
        tag.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tag.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(tag);
    }
    public PostTag updateTag(PostTag tag){
        tag.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(tag);
    }
    public void removeTag(Long id){
        repository.deleteById(id);
    }
}
