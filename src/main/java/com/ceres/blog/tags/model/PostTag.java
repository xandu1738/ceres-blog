package com.ceres.blog.tags.model;

import com.ceres.blog.posts.model.Posts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Posts> posts;

}
