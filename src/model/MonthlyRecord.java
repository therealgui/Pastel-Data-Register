package model;

import java.util.List;

public class MonthlyRecord {

	Archive<Record> listRecord;
	
	public MonthlyRecord() {
		this.listRecord = new Archive<>();
	}
	
	/**
	 * Add a new Record
	 * @param entry
	 * @return boolean
	 */
	public boolean addNewRecord(Record entry) {
		return this.listRecord.addElement(entry);
	}
	
	/**
	 * Remove a record
	 * @param record
	 * @return
	 */
	public boolean removeRecord(Record record) {
		return this.listRecord.removeElement(record);
	}
	
	/**
	 * Get all Records
	 * @return List<Record>
	 */
	public List<Record> listAllRecords(){
		return this.listRecord.getAll();
	}
}
