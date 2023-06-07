package com.ceres.blog.images.model;

import com.ceres.blog.posts.model.Posts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String filePath;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Posts post;
}
