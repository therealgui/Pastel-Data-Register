package presistence;

import model.MonthlyRecord;

import java.time.LocalDate;
import java.util.List;

public class MonthlyRecordDAO implements DAO<MonthlyRecord> {
    @Override
    public MonthlyRecord find(int id) {
        return null;
    }

    @Override
    public MonthlyRecord find(LocalDate date) {
        return null;
    }

    @Override
    public List<MonthlyRecord> findAll() {
        return null;
    }

    @Override
    public boolean save(MonthlyRecord obj) {
        return false;
    }

    @Override
    public boolean delete(MonthlyRecord obj) {
        return false;
    }

    @Override
    public boolean update(MonthlyRecord obj) {
        return false;
    }
}
