package ma.projet.dao;

import java.util.List;

public interface IDao<T, K> {
    T create(T t);
    T update(T t);
    void delete(K id);
    T findById(K id);
    List<T> findAll();
}
