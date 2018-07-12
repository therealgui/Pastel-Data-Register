package controller;

import model.Record;
import model.Subject;
import model.Observer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecordController{
	
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
	 * @return instance of Record or null
	 */
	public Record createNewRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA, LocalDate date) {
		try {
			this.newRegisto = new Record(receitaDiaria, despesaFatura, despesa, IVA, date);
			boolean result = dataPresistenceController.writeRecordInfoToFile(this.newRegisto);

			return result == true ? this.newRegisto : null;
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

		if(!this.newRegisto.getDate().isEqual(date)){
			return null;
		}

		try {
			this.newRegisto.setReceitaDiariaValor(receitaDiaria);
			this.newRegisto.setDespesaFaturaValor(despesaFatura);
			this.newRegisto.setDespesaValor(despesa);
			this.newRegisto.setIVAValor(IVA);
			return this.newRegisto;
		} catch(Exception e) {
			return null;
		}
	}
}
