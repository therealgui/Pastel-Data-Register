package controller;

import model.Record;
import presistence.DAO;
import presistence.DAOManager;
import java.util.List;

public class DataPresistenceController {
	private DAOManager daoManager;

	public DataPresistenceController(){
		daoManager = DAOManager.getInstance();
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
			return daoRecord.update(obj);
		}

		boolean resultRecord = daoRecord.insert(obj);
		boolean resultMonthly = daoMonthly.insert(obj);

		return resultRecord && resultMonthly;
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
}
