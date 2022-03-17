package controller;

import entity.Label;
import repository.LabelRepository;
import service.LabelService;
import util.ConnectionUtil;

import java.util.List;
import java.util.Optional;

public class LabelController {

    public final LabelService labelService = new LabelService(new LabelRepository());

    public void create(String name) {
        var label = Label.builder()
                .name(name)
                .build();
        labelService.create(label);
    }

    public Optional<Label> getById(Long labelId) {
        return labelService.getById(labelId);
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }

    public void update(Long id, String newName) {
        var label = Label.builder()
                .name(newName)
                .build();
        label.setId(id);
        labelService.update(label);
    }

    public boolean delete(Long id) {
        return labelService.delete(id);
    }
}
