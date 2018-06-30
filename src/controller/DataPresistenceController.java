package controller;

import model.Record;
import presistence.DataPresistence;
import util.Setting;

public class DataPresistenceController {
	private DataPresistence<Record> dtObj;

	public DataPresistenceController(){
		dtObj = new DataPresistence<>();
	}

	public boolean writeRecordInfoToFile(Record obj){
		return dtObj.writeDataToFile(obj, Setting.retrieveMainPath(), Setting.retrieveFileName());
	}
}
