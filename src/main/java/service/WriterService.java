package service;

import repository.WriterRepository;
import entity.Writer;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class WriterService {

    private final WriterRepository writerRepository;

    public Long create(Writer writer) {
        writerRepository.save(writer);
        return writer.getId();
    }

    public List<Writer> getAll() {
        return new ArrayList<>(writerRepository.
                getAll(writerRepository.WRITER_POST_GRAPH));
    }

    public Optional<Writer> getById(Long id) {
        return writerRepository.getById(id,
                Map.of(GraphSemantic.LOAD.getJpaHintName(), writerRepository.WRITER_POST_GRAPH));
    }


    public boolean update(Writer writer) {
        var returnedWriter = writerRepository.getById(writer.getId());
        returnedWriter.ifPresent(w -> {
            w.setFirstName(w.getFirstName());
            w.setLastName(w.getLastName());
            writerRepository.update(w);
        });
        return returnedWriter.isPresent();
    }

    public boolean delete(Long id) {
        var maybeWriter = writerRepository.getById(id);
        maybeWriter.ifPresent(writerRepository::delete);
        return maybeWriter.isPresent();
    }
}
