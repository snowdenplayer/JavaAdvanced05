package service;

import java.util.List;

public interface AbstractCrudService<T> {
    T create(T t);

    T read(Integer id);

    T update(T t);

    void delete(Integer id);

    List<T> readAll();
}
