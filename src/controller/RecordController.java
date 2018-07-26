package controller;

import model.Record;
import util.RecordState;

import java.time.LocalDate;
import java.util.List;

public class RecordController{
	
	private Record newRecord;
	private DataPresistenceController dataPresistenceController;
	
	public RecordController() {
		dataPresistenceController = new DataPresistenceController();
	}
	
	/**
	 * Create a new Record
	 * 
	 * @param receitaDiaria
	 * @param despesaFatura
	 * @param despesa
	 * @param IVA
	 * @return instance of Record or null
	 */
	public Record createNewRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA, LocalDate date) {
		try {
			this.newRecord = new Record(receitaDiaria, despesaFatura, despesa, IVA, date);
			this.newRecord.setState(RecordState.NEW);
			return this.newRecord;
		}catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * edit the current record
	 *
	 * @param receitaDiaria
	 * @param despesaFatura
	 * @param despesa
	 * @param IVA
	 * @return instance of Record or null
	 */
	public Record editRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA, LocalDate date) {

		this.newRecord = this.checkForIfRecordExists();

		if(this.newRecord == null){
			return null;
		}

		if(!this.newRecord.getDate().isEqual(date)){
			return null;
		}

		try {
			this.newRecord.setReceitaDiariaValor(receitaDiaria);
			this.newRecord.setDespesaFaturaValor(despesaFatura);
			this.newRecord.setDespesaValor(despesa);
			this.newRecord.setIVAValor(IVA);
			this.newRecord.setState(RecordState.EDITED);
			return this.newRecord;
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * get record object
	 *
	 * @return Record Object
	 */
	public Record getNewRecord(){
		return this.newRecord;
	}

	/**
	 * Validate if record already exists
	 *
	 * @param obj Record object
	 * @return boolean
	 */
	public boolean validateRecord(Record obj){
		if(this.newRecord == null){
			return false;
		}
		return this.newRecord.equals(obj);
	}

	private Record checkForIfRecordExists(){
		List<Record> list = dataPresistenceController.recordsInfoByMonth(LocalDate.now().getMonthValue());

		if(!list.isEmpty()) {
			Record record = list.get(list.size() - 1);

			if (record.getDate().equals(LocalDate.now())) {
				return record;
			}
		}

		return null;
	}

	/**
	 * Save to file record information
	 *
	 * @return boolean
	 */
	public boolean save(boolean editFlag){
		return dataPresistenceController.writeRecordInfoToFile(this.newRecord, editFlag);
	}
}
