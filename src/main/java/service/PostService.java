package service;

import entity.Writer;
import lombok.RequiredArgsConstructor;
import repository.LabelRepository;
import repository.PostRepository;
import entity.Post;
import org.hibernate.graph.GraphSemantic;
import repository.WriterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LabelRepository labelRepository;
    private final WriterRepository writerRepository;

    public Long create(Post newPost, Long writerId) {
        if(newPost.getContent().isEmpty()) {
            throw new NullPointerException("Post shouldn`t empty");
        }
        postRepository.save(newPost);
        var maybeWriter = writerRepository.getById(writerId, Map.of(GraphSemantic.LOAD.getJpaHintName(),
                writerRepository.WRITER_POST_GRAPH));
        maybeWriter.ifPresent(w -> {
            w.addPost(newPost);
            writerRepository.update(w);
        });
        return newPost.getId();
    }

    public List<Post> getAll() {
        return new ArrayList<>(postRepository.getAll(postRepository.WRITER_LABEL_GRAPH));
    }

    public Optional<Post> getById(Long id) {
        return postRepository.getById(id,
                Map.of(GraphSemantic.LOAD.getJpaHintName(), postRepository.WRITER_LABEL_GRAPH));
    }


    public boolean update(Post updatedPost) {
        var returnedPost = postRepository.getById(updatedPost.getId());
        returnedPost.ifPresent(p -> {
            p.setContent(updatedPost.getContent());
            postRepository.update(p);
        });
        return returnedPost.isPresent();
    }

    public boolean delete(Long id) {
        var maybePost = postRepository.getById(id);
        maybePost.ifPresent(postRepository::delete);
        return maybePost.isPresent();
    }

    public void addLabelToPost(Long labelId, Long postId) {
        var post = postRepository.getById(postId, Map.of(GraphSemantic.LOAD.getJpaHintName(),
                postRepository.WRITER_LABEL_GRAPH));
        post.ifPresent(p -> {
            p.addLabel(labelRepository.getById(labelId).orElseThrow());
            postRepository.update(p);
        });
    }

}
