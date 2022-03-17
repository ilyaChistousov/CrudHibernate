package service;

import entity.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LabelRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LabelServiceTest {

    private LabelRepository labelRepository;
    private LabelService labelService;

    @BeforeEach
    public void init() {
        labelRepository = mock(LabelRepository.class);
        labelService = new LabelService(labelRepository);
    }

    @Test
    public void saveMustReturnNewIdIfSuccess() {
        var label = Label.builder()
                .name("new Name")
                .build();
        label.setId(1L);

        when(labelRepository.save(any(Label.class))).thenReturn(label);

        assertEquals(label.getId(), labelService.create(label));
    }

    @Test
    public void getByIdMustReturnNotEmptyOptionalIfEntityExist() {
        var label = Label.builder()
                .name("new Name")
                .build();
        label.setId(1L);

        when(labelRepository.getById(any(Long.class))).thenReturn(Optional.of(label));

        assertEquals(label, labelService.getById(1L).orElse(new Label()));
    }

    @Test
    public void getByIdMustReturnEmptyOptionalIfEntityExist() {
        var label = Label.builder()
                .name("new Name")
                .build();
        label.setId(1L);

        when(labelRepository.getById(any(Long.class))).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), labelService.getById(1L));
    }

    @Test
    public void getAllTest() {
        var label = Label.builder()
                .name("new Name")
                .build();
        label.setId(1L);
        var label2 = Label.builder()
                .name("name")
                .build();
        label.setId(2L);
        var labels = new ArrayList<>(List.of(label, label2));

        when(labelRepository.getAll()).thenReturn(labels);

        assertEquals(labels.size(), labelService.getAll().size());
    }

    @Test
    public void updateSuccessTest() {
        var labelServiceMock = mock(LabelService.class);
        var label = Label.builder()
                .name(" Name")
                .build();
        label.setId(1L);

        when(labelServiceMock.update(label)).thenReturn(true);

        assertTrue(labelServiceMock.update(label));
    }

    @Test
    public void deleteReturnTrueIfEntityExist() {
        var labelServiceMock = mock(LabelService.class);
        var label = Label.builder()
                .name("new Name")
                .build();
        label.setId(1L);


        when(labelServiceMock.delete(label.getId())).thenReturn(true);

        assertTrue(labelServiceMock.delete(1L));
    }

    @Test
    public void deleteReturnFalseIfEntityNotExist() {
        var labelServiceMock = mock(LabelService.class);
        var label = Label.builder()
                .name("new Name")
                .build();
        label.setId(1L);

        when(labelServiceMock.delete(label.getId())).thenReturn(true);

        assertFalse(labelServiceMock.delete(2L));
    }
}
