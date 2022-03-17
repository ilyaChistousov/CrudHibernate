package repository;

import entity.BaseEntity;
import lombok.Cleanup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import util.ConnectionUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public abstract class BaseRepository<K extends Serializable, E extends BaseEntity> implements Repository<K, E> {

    private final Class<E> clazz;
    private final SessionFactory sessionFactory = ConnectionUtil.getSession().getSessionFactory();


    @Override
    public E save(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }


    @Override
    public List<E> getAll(Object property) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaQuery<E> query = session.getCriteriaBuilder().createQuery(clazz);
        query.from(clazz);
        var resultList = session.createQuery(query)
                .setHint(GraphSemantic.LOAD.getJpaHintName(), property)
                .getResultList();
        session.getTransaction().commit();
        return resultList;
    }

    @Override
    public Optional<E> getById(K id, Map<String, Object> properties) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        var maybe = Optional.ofNullable(session.find(clazz, id, properties));
        session.getTransaction().commit();
        return maybe;
    }

    @Override
    public void delete(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public void update(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

}
