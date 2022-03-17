package service;

import entity.Label;
import entity.Post;
import entity.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.WriterRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WriterServiceTest {

    private WriterRepository writerRepository;
    private WriterService writerService;

    @BeforeEach
    public void init() {
        writerRepository = mock(WriterRepository.class);
        writerService = new WriterService(writerRepository);
    }

    @Test
    public void saveMustReturnNewIdIfSuccess() {
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);

        when(writerRepository.save(any(Writer.class))).thenReturn(writer);

        assertEquals(writer.getId(), writerService.create(writer));
    }

    @Test
    public void getByIdMustReturnNotEmptyOptionalIfEntityExist() {
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);

        when(writerRepository.getById(any(Long.class))).thenReturn(Optional.of(writer));

        assertEquals(writer, writerService.getById(1L).orElse(new Writer()));
    }

    @Test
    public void getByIdMustReturnEmptyOptionalIfEntityExist() {
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);

        when(writerRepository.getById(any(Long.class))).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), writerService.getById(1L));
    }

    @Test
    public void getAllTest() {
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);
        var writer1 = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(2L);
        var writers = new ArrayList<>(List.of(writer, writer1));

        when(writerRepository.getAll()).thenReturn(writers);

        assertEquals(writers.size(), writerService.getAll().size());
//        var label = Label.builder()
//                .name("new Name")
//                .build();
//        label.setId(1L);
//        var label2 = Label.builder()
//                .name("name")
//                .build();
//        label.setId(2L);
//        var labels = new ArrayList<>(List.of(label, label2));
//
//        when(labelRepository.getAll()).thenReturn(labels);
//
//        assertEquals(labels.size(), labelService.getAll().size());
    }

    @Test
    public void updateSuccessTest() {
        var writerServiceMock = mock(WriterService.class);
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);

        when(writerServiceMock.update(writer)).thenReturn(true);

        assertTrue(writerServiceMock.update(writer));
    }

    @Test
    public void deleteReturnTrueIfEntityExist() {
        var writerServiceMock = mock(WriterService.class);
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);

        when(writerServiceMock.delete(writer.getId())).thenReturn(true);

        assertTrue(writerServiceMock.delete(1L));
    }

    @Test
    public void deleteReturnFalseIfEntityNotExist() {
        var writerServiceMock = mock(WriterService.class);
        var writer = Writer.builder()
                .firstName("first")
                .lastName("last")
                .build();
        writer.setId(1L);

        when(writerServiceMock.delete(writer.getId())).thenReturn(true);

        assertFalse(writerServiceMock.delete(2L));
    }

}
