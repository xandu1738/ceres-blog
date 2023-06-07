package com.ceres.blog.posts.service;

import com.ceres.blog.posts.model.Posts;
import com.ceres.blog.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService{
    private final PostsRepository repository;
//    @PostConstruct
//    void initPost(){
//        Faker faker = new Faker();
//        Posts posts = IntStream.rangeClosed(1,20)
//                .mapToObj(
//                        i->new Posts(
//                                new Random().nextLong(),
//                                faker.book().title(),
//                                20,
//                                new Timestamp(System.currentTimeMillis()),
//                                new Timestamp(System.currentTimeMillis()),
//                                null,
//                                null,
//                                new Likes(),
//                                null
//
//                        )
//                ).collect(Collectors.toList());
//    }
    @Override
    public List<Posts> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Posts> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Posts savePost(Posts post) {
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        return repository.save(post);
    }

    @Override
    public Posts updatePost(Posts post) {
        post.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        return repository.save(post);
    }

    @Override
    public String removePost(Long id) {
        repository.deleteById(id);
        return "Item removed.";
    }
}
