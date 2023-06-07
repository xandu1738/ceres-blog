package com.ceres.blog.comments.model;

import com.ceres.blog.posts.model.Posts;
import com.ceres.blog.users.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "comment", nullable = false)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Posts post;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (id != comments.id) return false;
        if (user != null ? !user.equals(comments.user) : comments.user != null) return false;
        if (post != null ? !post.equals(comments.post) : comments.post != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        return result;
    }
}
