package presistence;

import java.time.LocalDate;
import java.util.List;

public interface DAO<T> {

    T find(int id);
    T find(LocalDate date);
    List<T> findAll();
    boolean save(T obj);
    boolean delete(T obj);
    boolean update(T obj);
}
