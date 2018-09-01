package controller;

import model.Record;
import presistence.DAO;
import presistence.DAOManager;
import java.util.List;
import java.util.Properties;
import util.PropertiesLoader;

public class DataPresistenceController {
	private DAOManager daoManager;
	private String propFile;

	public DataPresistenceController(){
		daoManager = DAOManager.getInstance();
		propFile = "db_info_testing.properties";
		Properties prop = PropertiesLoader.loadProperties(propFile);
		String db_driver = prop.getProperty("db_driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");

		daoManager.createConnection(db_driver, url, user, password);
	}

	/**
	 * Get all records from database
	 * @return list of record objects
	 */
	public List<Record> allRecords(){
	    return daoManager.getDAO("record").findAll();
    }

	/**
	 * Save record info into database
	 * @param obj Record object
	 * @return boolean
	 */
	public boolean saveRecord(Record obj, boolean editFlag){

		DAO daoRecord = daoManager.getDAO("record");
		DAO daoMonthly = daoManager.getDAO("monthly");

		if(editFlag){
			return daoRecord.update(obj,0);
		}

		boolean resultRecord = daoRecord.insert(obj);
		boolean resultMonthly = daoMonthly.insert(obj);

		boolean resultCloseConnection = daoManager.closeConnection();

		return resultRecord && resultMonthly && resultCloseConnection;
	}

	/**
	 * Retreive all Objects of type Record from a specified month
	 * @param month int
	 * @return List of Record Objects
	 */
	public List<Record> recordsInfoByMonth(int month){
		DAO dao = daoManager.getDAO("monthly");

		return dao.findByMonth(month);
	}

	public boolean backup(){
	    return this.daoManager.backupDatabase();
    }
}
