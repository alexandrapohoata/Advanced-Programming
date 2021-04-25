package repositories;

import java.util.List;

public abstract class AbstractRepository<E> {
    public abstract void create(E e);
    public abstract E findById(String id);

    public abstract List<E> findByName(String name);
}