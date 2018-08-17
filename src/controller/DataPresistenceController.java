package controller;

import model.Record;
import presistence.DataPresistence;
import util.Setting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataPresistenceController {
	private DataPresistence<Record> dtObj;

	public DataPresistenceController(){
		dtObj = new DataPresistence<>();
	}

	/**
	 * read file with all the records info
	 * @return list of strings
	 */
	public List<String> readRecordsInfoFromFile(){
	    return dtObj.readDataFromFile(Setting.retrieveMainPath(), Setting.retrieveFileName());
    }

	/**
	 * write to file
	 * @param obj Record object
	 * @return boolean
	 */
	public boolean writeRecordInfoToFile(Record obj, boolean editFlag){

		if(editFlag){
			return dtObj.rewriteDataToFile(obj, Setting.retrieveMainPath(), Setting.retrieveFileName());
		}

		return dtObj.writeDataToFile(obj, Setting.retrieveMainPath(), Setting.retrieveFileName());
	}

	/**
	 * Retreive all Objects of type Record from a specified month
	 * @param month int
	 * @return List of Record Objects
	 */
	public List<Record> recordsInfoByMonth(int month){
		List<String> list = this.readRecordsInfoFromFile();
		List<Record> listRecords = new ArrayList<>();

		for(String str : list){
			String[] split = str.split(";");
			LocalDate date = LocalDate.parse(split[0]);

			if(date.getMonth().getValue() == month){
				listRecords.add(new Record(Double.parseDouble(split[1]),Double.parseDouble(split[2]),Double.parseDouble(split[3]),Double.parseDouble(split[4]), date));
			}
		}

		return listRecords;
	}
}
