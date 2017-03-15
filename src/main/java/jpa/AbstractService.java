package jpa;

import java.lang.reflect.ParameterizedType;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.EntityModel;

public abstract class AbstractService<T extends EntityModel> implements ServiceInterface<T> {

    public AbstractService() {
    }

    @PersistenceContext
    protected EntityManager manager;

    @PostConstruct
    public void ini() {

    }

    @Override
    public void delete(T t) {
        manager.remove(manager.merge(t));
    }

    @Override
    public void add(T t) {
        manager.persist(t);
    }

    @Override
    public T get(Long id) {
        return (T) manager.find((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], id);
    }

    @Override
    public void update(T t) {
        manager.merge(t);
    }

    @PreDestroy
    public void close() {

    }

}
