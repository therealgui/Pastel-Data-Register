package presistence;

import java.sql.*;

public class DAOManager {

    private Connection conn;
    private static DAOManager instance;

    private DAOManager(){
        //make class singleton
    }

    /**
     * Get instance of DAOManager
     * @return DAOManager object
     */
    public static DAOManager getInstance(){
        return instance == null ? (instance = new DAOManager()) : instance;
    }

    /**
     * Get instance of Connection
     * @return Connection Object
     */
    private Connection getConnection(){
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

    /**
     * Close connection
     */
    public void closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DAO getDAO(String name){
        this.conn = this.getConnection();

        if(name.equalsIgnoreCase("record")){
            return new RecordDAO(conn);
        }
        if(name.equalsIgnoreCase("monthly")){
            return new MonthlyRecordDAO(conn);
        }

        return null;
    }

}
