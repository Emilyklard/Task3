package dao;

import java.util.List;
public interface Dao <K,T> {
    List<T> findAll();
    T findById(K id);
    void delete(K id);
    int update(T entity);
    T save(T entity);
}
