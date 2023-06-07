package com.ceres.blog.tags.repository;

import com.ceres.blog.tags.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<PostTag,Long> {
    Optional<PostTag> findByName(String name);
}
