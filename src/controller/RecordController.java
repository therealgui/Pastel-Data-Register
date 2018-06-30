package controller;

import model.Record;

import java.time.LocalDate;

public class RecordController {
	
	private Record newRegisto;
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
	 * @param toReInsert
	 * @return boolean
	 */
	public boolean createNewRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA, boolean toReInsert) {
		if(toReInsert) {
			return this.editRecord(receitaDiaria, despesaFatura, despesa, IVA);
		}
		else {
			return this.createNewRegisto(receitaDiaria, despesaFatura, despesa, IVA);
		}
	}
	
	/**
	 * 
	 * @param receitaDiaria
	 * 
	 * @param despesaFatura
	 * @param despesa
	 * @param IVA
	 * @return
	 */
	private boolean editRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA) {

		if(!this.newRegisto.getDate().isEqual(LocalDate.now())){
			return false;
		}

		try {
			this.newRegisto.setReceitaDiariaValor(receitaDiaria);
			this.newRegisto.setDespesaFaturaValor(despesaFatura);
			this.newRegisto.setDespesaValor(despesa);
			this.newRegisto.setIVAValor(IVA);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * create a new Record object
	 * 
	 * @param receitaDiaria
	 * @param despesaFatura
	 * @param despesa
	 * @param IVA
	 * @return
	 */
	private boolean createNewRegisto(double receitaDiaria, double despesaFatura, double despesa, double IVA) {
		try {
			this.newRegisto = new Record(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());
			return dataPresistenceController.writeRecordInfoToFile(this.newRegisto);
		}catch(Exception e) {
			return false;
		}
	}
}
