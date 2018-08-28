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
    public Connection getConnection(){
        return this.conn;
    }

    /**
     * Create a Connection Object
     * @return Connection Object
     */
    public boolean createConnection(String dbDriver, String url, String user, String password){
        boolean result = false;

        try {
            Class.forName (dbDriver);
            conn = DriverManager.getConnection(url,user,password);

            result =  conn.isValid(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    /**
     * Close connection
     */
    public boolean closeConnection(){
        boolean result = false;

        if(this.conn == null){
            return result;
        }

        try {
            this.conn.close();

            result =  this.conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Get a implementation of the DAO interface
     * @param name name of class
     * @return instance of a implementation of the DAO interface
     */
    public DAO getDAO(String name){
        if(this.conn == null){
            return null;
        }

        try {
            if(this.conn.isClosed()){
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(name.equalsIgnoreCase("record")){
            return new RecordDAO(conn);
        }
        if(name.equalsIgnoreCase("monthly")){
            return new MonthlyRecordDAO(conn);
        }

        return null;
    }

}
