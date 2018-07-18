package controller;

import model.Record;

import java.time.LocalDate;

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

		if(!this.newRecord.getDate().isEqual(date)){
			return null;
		}

		try {
			this.newRecord.setReceitaDiariaValor(receitaDiaria);
			this.newRecord.setDespesaFaturaValor(despesaFatura);
			this.newRecord.setDespesaValor(despesa);
			this.newRecord.setIVAValor(IVA);
			return this.newRecord;
		} catch(Exception e) {
			return null;
		}
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

	/**
	 * Save to file record information
	 *
	 * @return boolean
	 */
	public boolean save(){
		return dataPresistenceController.writeRecordInfoToFile(this.newRecord);
	}
}
