package presistence;

import model.Record;

import java.time.LocalDate;
import java.util.List;

public class RecordDAO implements DAO<Record> {
    @Override
    public Record find(int id) {
        return null;
    }

    @Override
    public Record find(LocalDate date) {
        return null;
    }

    @Override
    public List<Record> findAll() {
        return null;
    }

    @Override
    public boolean save(Record obj) {
        return false;
    }

    @Override
    public boolean delete(Record obj) {
        return false;
    }

    @Override
    public boolean update(Record obj) {
        return false;
    }
}
