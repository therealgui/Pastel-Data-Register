package presistence;

import model.MonthlyRecord;
import model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOManagerTest {

    private DAOManager daoManager;

    @BeforeEach
    void setUp(){
        daoManager = DAOManager.getInstance();
    }

    @Test
    void createConnection_AllDataBaseInfoProvided_True() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = "test";
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertTrue(result);

        daoManager.closeConnection();
    }

    @Test
    void createConnection_MissingUrl_False(){
        String driver = "org.h2.Driver";
        String url = null;
        String user = "test";
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertFalse(result);
    }

    @Test
    void createConnection_MissingUser_False(){
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = null;
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertFalse(result);
    }

    @Test
    void createConnection_MissingPassword_False(){
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = "test";
        String password = null;

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertFalse(result);

        daoManager.closeConnection();
    }

    @Test
    void createConnection_MissingAllInfo_False(){
        String driver = null;
        String url = null;
        String user = null;
        String password = null;

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertFalse(result);
    }

    @Test
    void closeConnection_AllDataBaseInfoProvided_True() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = "test";
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertTrue(result);

        assertTrue(daoManager.closeConnection());
    }

    @Test
    void closeConnection_ConnectionNotCreated_False() {
        assertFalse(daoManager.closeConnection());
    }

    @Test
    void getDAO_AllDataBaseInfoProvidedForRecordDAO_NotNull() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = "test";
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertTrue(result);

        DAO dao = daoManager.getDAO("record");

        assertTrue(dao != null);

        assertTrue(dao instanceof RecordDAO);

        daoManager.closeConnection();
    }

    @Test
    void getDAO_AllDataBaseInfoProvidedForMonthlyRecordDAO_NotNull() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = "test";
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertTrue(result);

        DAO dao = daoManager.getDAO("monthly");

        assertTrue(dao != null);

        assertTrue(dao instanceof MonthlyRecordDAO);

        daoManager.closeConnection();
    }

    @Test
    void getDAO_ConnectionNotCreated_Null() {
        DAO dao = daoManager.getDAO("record");

        assertNull(dao);
    }

    @Test
    void getDAO_ConnectionClosed_Null() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:~/test";
        String user = "test";
        String password = "test";

        boolean result = daoManager.createConnection(driver, url, user, password);

        assertTrue(result);

        daoManager.closeConnection();

        DAO dao = daoManager.getDAO("record");

        assertNull(dao);
    }
}