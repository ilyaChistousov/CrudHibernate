package controller;

import entity.Writer;
import repository.WriterRepository;
import service.WriterService;

import java.util.List;
import java.util.Optional;

public class WriterController {

    private final WriterService writerService = new WriterService(new WriterRepository());

    public void create(String firstName, String lastName) {
        var writer = Writer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        writerService.create(writer);
    }

    public Optional<Writer> getById(Long writerId) {
        return writerService.getById(writerId);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public void update(Long writerId, String newFirstName, String newLastName) {
        var writer = Writer.builder()
                .firstName(newFirstName)
                .lastName(newLastName)
                .build();
        writer.setId(writerId);
        writerService.update(writer);
    }

    public boolean delete(Long id) {
        return writerService.delete(id);
    }

}
