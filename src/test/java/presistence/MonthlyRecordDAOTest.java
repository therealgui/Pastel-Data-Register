package presistence;

import model.Record;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PropertiesLoader;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyRecordDAOTest {

    private DAOManager daoManager;
    private String propFile;
    String db_driver;
    String url;
    String user;
    String password;
    Record record;
    LocalDate defaultDate;

    @BeforeEach
    void setUp() {
        daoManager = DAOManager.getInstance();
        propFile = "db_info_testing.properties";
        Properties prop = PropertiesLoader.loadProperties(propFile);
        db_driver = prop.getProperty("db_driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
        double receitaDiaria = 10;
        double despesaFatura = 10;
        double despesa = 10;
        double iva = 23;
        defaultDate = LocalDate.of(2018, Month.MARCH, 1);
        record = new Record(receitaDiaria, despesaFatura, despesa, iva, defaultDate);

        daoManager.createConnection(db_driver, url, user, password);

        try {
            Statement stmt = daoManager.getConnection().createStatement();
            stmt.executeUpdate("delete from monthlyRecord;");
            stmt.executeUpdate("delete from record;");
            stmt.executeUpdate("alter table record alter column id restart with 1;");

            for(int i=0; i<5; i++){
                LocalDate dateTemp = record.getDate().plusMonths(i);
                stmt.executeUpdate("insert into record(daily_revenue,bill_expense,expense,tax,entry_date) " +
                        "values("+(record.getReceitaDiariaValor()+i)+"," +
                        ""+(record.getDespesaFaturaValor()+i)+
                        ","+(record.getDespesaValor()+i)+"," +
                        ""+(record.getIVAValor()+i)+"," +
                        "to_date('"+(dateTemp.plusDays(i))+"','yyyy-mm-dd'));");

                stmt.executeUpdate("insert into monthlyrecord values("+(i+1)+","+dateTemp.getMonth().getValue()+","+dateTemp.getYear()+");");
            }


            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById_CorrectCenario_GetCorrectResult() {

        DAO monthDao = daoManager.getDAO("monthly");
        int id = 3;

        Record result = (Record) monthDao.findById(id);
        LocalDate date = defaultDate.plusMonths(2);
        date = date.plusDays(2);

        assertTrue(result.getReceitaDiariaValor() == (record.getReceitaDiariaValor()+2));
        assertTrue(result.getDespesaFaturaValor() == (record.getDespesaFaturaValor()+2));
        assertTrue(result.getDespesaValor() == (record.getDespesaValor()+2));
        assertTrue(result.getIVAValor() == (record.getIVAValor()+2));
        assertTrue(result.getDate().isEqual(date));

        daoManager.closeConnection();
    }

    @Test
    void findByMonth_CorrectCenario_GetCorrectResult() {
        DAO monthDao = daoManager.getDAO("monthly");
        int month = 5;

        List<Record> result = (List<Record>) monthDao.findByMonth(month);
        LocalDate date = defaultDate.plusMonths(2);
        date = date.plusDays(2);

        assertTrue(result.get(0).getReceitaDiariaValor() == (record.getReceitaDiariaValor()+2));
        assertTrue(result.get(0).getDespesaFaturaValor() == (record.getDespesaFaturaValor()+2));
        assertTrue(result.get(0).getDespesaValor() == (record.getDespesaValor()+2));
        assertTrue(result.get(0).getIVAValor() == (record.getIVAValor()+2));
        assertTrue(result.get(0).getDate().isEqual(date));

        daoManager.closeConnection();
    }

    @Test
    void findByDate_CorrectCenario_GetCorrectResult() {
        DAO monthDao = daoManager.getDAO("monthly");

        LocalDate date = defaultDate.plusMonths(2);
        date = date.plusDays(2);
        Record result = (Record) monthDao.findByDate(date);

        assertTrue(result.getReceitaDiariaValor() == (record.getReceitaDiariaValor()+2));
        assertTrue(result.getDespesaFaturaValor() == (record.getDespesaFaturaValor()+2));
        assertTrue(result.getDespesaValor() == (record.getDespesaValor()+2));
        assertTrue(result.getIVAValor() == (record.getIVAValor()+2));
        assertTrue(result.getDate().isEqual(date));

        daoManager.closeConnection();

    }

    @Test
    void insert() {
    }
}