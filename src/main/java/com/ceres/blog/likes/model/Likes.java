package com.ceres.blog.likes.model;

import com.ceres.blog.posts.model.Posts;
import com.ceres.blog.users.model.Users;
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
public class Likes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn
    private Posts post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Likes likes = (Likes) o;

        if (id != likes.id) return false;
        if (user != null ? !user.equals(likes.user) : likes.user != null) return false;
        if (post != null ? !post.equals(likes.post) : likes.post != null) return false;
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
