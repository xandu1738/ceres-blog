package com.ceres.blog.posts.model;

import com.ceres.blog.comments.model.Comments;
import com.ceres.blog.images.model.PostImage;
import com.ceres.blog.likes.model.Likes;
import com.ceres.blog.tags.model.PostTag;
import com.ceres.blog.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    private String title;
    @Basic
    @Column(name = "content", nullable = true, length = -1)
    private String content;
    @Basic
    @Column(name = "total_likes", nullable = true, length = -1)
    private int totalLikes;
    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private Users user;
    @OneToMany(mappedBy = "post")
    private List<PostImage> images;
    @Basic
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_on", nullable = true)
    private Timestamp updatedOn;
    @OneToMany(mappedBy = "post")
    private Collection<Likes> likes;
    @OneToMany(mappedBy = "post")
    private Collection<Comments> comments;
    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<PostTag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posts posts = (Posts) o;

        if (id != posts.id) return false;
        if (content != null ? !content.equals(posts.content) : posts.content != null) return false;
        if (createdAt != null ? !createdAt.equals(posts.createdAt) : posts.createdAt != null) return false;
        if (updatedOn != null ? !updatedOn.equals(posts.updatedOn) : posts.updatedOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedOn != null ? updatedOn.hashCode() : 0);
        return result;
    }
}
