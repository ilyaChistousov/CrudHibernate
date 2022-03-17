package service;

import entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LabelRepository;
import repository.PostRepository;
import repository.WriterRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    private PostRepository postRepository;
    private PostService postService;

    @BeforeEach
    public void init() {
        postRepository = mock(PostRepository.class);
        LabelRepository labelRepository = mock(LabelRepository.class);
        WriterRepository writerRepository = mock(WriterRepository.class);
        postService = new PostService(postRepository, labelRepository, writerRepository);
    }

    @Test
    public void saveMustReturnNewIdIfSuccess() {
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);

        when(postRepository.save(any(Post.class))).thenReturn(post);

        assertEquals(post.getId(), postService.create(post, 1L));
    }

    @Test
    public void getByIdMustReturnNotEmptyOptionalIfEntityExist() {
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);

        when(postRepository.getById(any(Long.class))).thenReturn(Optional.of(post));

        assertEquals(post, postService.getById(1L).orElse(new Post()));
    }

    @Test
    public void getByIdMustReturnEmptyOptionalIfEntityExist() {
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);

        when(postRepository.getById(any(Long.class))).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), postService.getById(1L));
    }

    @Test
    public void getAllTest() {
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);
        var post1 = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);
        var posts = new ArrayList<>(List.of(post, post1));

        when(postRepository.getAll()).thenReturn(posts);

        assertEquals(posts.size(), postService.getAll().size());
    }

    @Test
    public void updateSuccessTest() {
        var postServiceMock = mock(PostService.class);
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);

        when(postServiceMock.update(post)).thenReturn(true);

        assertTrue(postServiceMock.update(post));
    }

    @Test
    public void deleteReturnTrueIfEntityExist() {
        var postServiceMock = mock(PostService.class);
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);

        when(postServiceMock.delete(post.getId())).thenReturn(true);

        assertTrue(postServiceMock.delete(1L));
    }

    @Test
    public void deleteReturnFalseIfEntityNotExist() {
        var postServiceMock = mock(PostService.class);
        var post = Post.builder()
                .content("Content")
                .build();
        post.setId(1L);

        when(postServiceMock.delete(post.getId())).thenReturn(true);

        assertTrue(postServiceMock.delete(2L));
    }

}
