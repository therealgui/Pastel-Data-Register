package presistence;

import java.sql.*;

public class DAOManager {

    private Connection conn;
    private DAOManager instance;

    private DAOManager(){
        //make class singleton
    }

    /**
     * Get instance of DAOManager
     * @return DAOManager object
     */
    public DAOManager getInstance(){
        return instance == null ? (instance = new DAOManager()) : instance;
    }

    /**
     * Get instance of Connection
     * @return Connection Object
     */
    public Connection getConnection(){
        if(conn == null){
            conn = createConnection();
        }

        return conn;
    }

    /**
     * Create a Connection Object
     * @return Connection Object
     */
    private Connection createConnection(){
        try {
            Class.forName ("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "test", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public DAO getDAO(String name){

        if(name.equalsIgnoreCase("record")){
            return new RecordDAO();
        }
        if(name.equalsIgnoreCase("monthly")){
            return new MonthlyRecordDAO();
        }

        return null;
    }

}
