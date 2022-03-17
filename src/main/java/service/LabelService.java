package service;

import lombok.RequiredArgsConstructor;
import repository.LabelRepository;
import entity.Label;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    public Long create(Label newLabel) {
        if(newLabel.getName().isEmpty()) {
            throw new NullPointerException("Name should`t be empty");
        }
        var savedLabel = labelRepository.save(newLabel);
        return savedLabel.getId();
    }

    public List<Label> getAll() {
        return new ArrayList<>(labelRepository.getAll());
    }

    public Optional<Label> getById(Long id) {
        return labelRepository.getById(id);
    }

    public boolean update(Label updatedLabel) {
        var returnedLabel = labelRepository.getById(updatedLabel.getId());
        returnedLabel.ifPresent(label -> {
            label.setName(updatedLabel.getName());
            labelRepository.update(label);
        });
        return returnedLabel.isPresent();
    }

    public boolean delete(Long id) {
        var maybeLabel = labelRepository.getById(id);
        maybeLabel.ifPresent(labelRepository::delete);
        return maybeLabel.isPresent();
    }
}
