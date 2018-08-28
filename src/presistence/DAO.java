package presistence;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface DAO<T> {

    T findById(int id);
    List<T> findByMonth(int month);
    T findByDate(LocalDate date);
    List<T> findAll();
    boolean insert(T obj);
    boolean delete(T obj, int id);
    boolean update(T obj, int id);
}
