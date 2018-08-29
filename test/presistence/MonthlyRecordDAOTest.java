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

    @BeforeEach
    void setUp() {
        daoManager = DAOManager.getInstance();
        propFile = "db_info_testing.properties";
        Properties prop = PropertiesLoader.getProperties(propFile);
        db_driver = prop.getProperty("db_driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
        double receitaDiaria = 10;
        double despesaFatura = 10;
        double despesa = 10;
        double iva = 23;
        LocalDate date = LocalDate.of(2018, Month.MARCH, 1);
        record = new Record(receitaDiaria, despesaFatura, despesa, iva, date);

        daoManager.createConnection(db_driver, url, user, password);

        try {
            Statement stmt = daoManager.getConnection().createStatement();
            stmt.executeUpdate("delete from monthlyRecord;");
            stmt.executeUpdate("delete from record;");
            stmt.executeUpdate("alter table record alter column id restart with 1;");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByMonth() {
    }

    @Test
    void findByDate() {
    }

    @Test
    void insert() {
    }
}