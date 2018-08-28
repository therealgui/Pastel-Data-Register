package presistence;

import model.Record;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecordDAO implements DAO<Record> {

    private static final Logger LOGGER = Logger.getLogger(RecordDAO.class.getName());
    private Connection conn;

    public RecordDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Record findById(int id) {
        return null;
    }

    @Override
    public List<Record> findByMonth(int month) {
        return null;
    }

    @Override
    public Record findByDate(LocalDate date) {
        return null;
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

            int result = stmt.executeUpdate("insert into record(daily_revenue,bill_expense,expense,tax,entry_date) " +
                    "values("+obj.getReceitaDiariaValor()+"," +
                    ""+obj.getDespesaFaturaValor()+
                    ","+obj.getDespesaValor()+"," +
                    ""+obj.getIVAValor()+"," +
                    "to_date('"+obj.getDate()+"','yyyy-mm-dd'));");

            return result > 0 ? true : false;

        } catch(SQLException ex){
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
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
        Statement stmt = null;

        try{
            stmt = conn.createStatement();

            ResultSet resultQuery= stmt.executeQuery("select id from record where entry_date = to_date('"+obj.getDate()+"','yyyy-mm-dd');");
            resultQuery.next();

            int result = stmt.executeUpdate("update record set " +
                    "daily_revenue="+obj.getReceitaDiariaValor()+", " +
                    "bill_expense="+obj.getDespesaFaturaValor()+", " +
                    "expense="+obj.getDespesaValor()+"," +
                    "tax="+obj.getIVAValor()+"," +
                    "entry_date=to_date('"+obj.getDate()+"','yyyy-mm-dd') where id="+resultQuery.getInt("id")+";");

            return result > 0 ? true : false;

        } catch(SQLException ex){
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }

        return false;
    }
}
