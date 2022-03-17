package repository;

import entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static java.util.Collections.emptyMap;

public interface Repository<K extends Serializable, E extends BaseEntity> {

    E save(E entity);

    Optional<E> getById(K id, Map<String, Object> properties);

    default Optional<E> getById(K id) {
        return getById(id, emptyMap());
    }

    List<E> getAll(Object property);

    default List<E> getAll() {
        return getAll(new Object());
    }

    void delete(E entity);

    void update(E entity);
}
