package controller;

import entity.Post;
import repository.LabelRepository;
import repository.PostRepository;
import repository.WriterRepository;
import service.PostService;

import java.util.List;
import java.util.Optional;

public class PostController {

    public final PostService postService = new PostService(
            new PostRepository(),
            new LabelRepository(),
            new WriterRepository());


    public void create(String content, Long writerId) {
        var post = Post.builder()
                .content(content)
                .build();
        postService.create(post, writerId);
    }

    public Optional<Post> getById(Long postId) {
        return postService.getById(postId);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public void update(Long id, String newContent) {
        var post = Post.builder()
                .content(newContent)
                .build();
        post.setId(id);
        postService.update(post);
    }

    public boolean delete(Long id) {
        return postService.delete(id);
    }

    public void addLabelToPost(Long labelId, Long postId) {
        postService.addLabelToPost(labelId, postId);
    }
}
