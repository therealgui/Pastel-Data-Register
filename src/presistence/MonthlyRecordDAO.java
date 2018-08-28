package presistence;

import model.Record;
import util.RecordState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRecordDAO implements DAO<Record> {

    private Connection conn;

    public MonthlyRecordDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Record findById(int id) {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("select * from record where id = "+id+";");

            result.next();
            double dailyRevenue = result.getDouble("daily_revenue");
            double billExpense = result.getDouble("bill_expense");
            double expense = result.getDouble("expense");
            double tax = result.getDouble("tax");
            LocalDate date = result.getDate("entry_date").toLocalDate();
            Record newRecord = new Record(dailyRevenue, billExpense, expense, tax, date);
            newRecord.setState(RecordState.PERSISTED);

            return newRecord;

        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<Record> findByMonth(int month) {
        Statement stmt = null;
        List<Record> list = new ArrayList<>();

        try {
            stmt = conn.createStatement();

            ResultSet result= stmt.executeQuery("select id_record from monthlyRecord where month_number = "+month+";");

            while(result.next()){
                int id = result.getInt("id_record");

                list.add(this.findById(id));
            }

            return list;

        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public Record findByDate(LocalDate date) {
        Statement stmt = null;
        Record rec = null;

        try {
            stmt = conn.createStatement();

            ResultSet result= stmt.executeQuery("select id_record from monthlyRecord where month_number = "+date.getMonth().getValue()+";");

            while(result.next()){
                int id = result.getInt("id_record");

                rec = this.findById(id);

                if(rec.getDate().isEqual(date)){
                    return rec;
                }

                rec = null;
            }

            return rec;

        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rec;
    }

    @Override
    public List<Record> findAll() {
        return null;
    }

    @Override
    public boolean insert(Record obj) {
        Statement stmt = null;

        try{
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("select id from record where entry_date=to_date('"+obj.getDate()+"','yyyy-mm-dd');");

            resultSet.next();
            int id = resultSet.getInt("id");

            int result = stmt.executeUpdate("insert into monthlyrecord(id_record, month_number, year) " +
                    "values("+id+","+
                    LocalDate.now().getMonth().getValue()
                    +","+LocalDate.now().getYear()+");");

            return result > 0 ? true : false;

        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean delete(Record obj, int id) {
        return false;
    }

    @Override
    public boolean update(Record obj, int id) {
        return false;
    }
}
