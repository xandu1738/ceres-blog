package com.ceres.blog.images.repository;

import com.ceres.blog.images.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<PostImage,Long> {
    Optional<PostImage> findByName(String name);
}
