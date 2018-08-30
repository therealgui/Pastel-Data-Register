package model;

import util.RecordState;

import java.time.*;

public class Record implements Comparable<Record>{

	private double receitaDiariaValor;
	private double despesaFaturaValor;
	private double despesaValor;
	private double IVAValor;
	private LocalDate date;
	private RecordState state;
	
	public Record(double receitaDiariaValor, double despesaFaturaValor, double despesaValor, double IVAValor, LocalDate date) {
		this.setReceitaDiariaValor(receitaDiariaValor);
		this.setDespesaFaturaValor(despesaFaturaValor);
		this.setDespesaValor(despesaValor);
		this.setIVAValor(IVAValor);
		this.setDate(date);
		this.state = RecordState.EMPTY;
	}
	
	/**
	 * @return the receitaDiariaValor
	 */
	public double getReceitaDiariaValor() {
		return receitaDiariaValor;
	}

	/**
	 * @param receitaDiariaValor the receitaDiariaValor to set
	 */
	public void setReceitaDiariaValor(double receitaDiariaValor) {
		this.receitaDiariaValor = receitaDiariaValor;
	}

	/**
	 * @return the despesaFaturaValor
	 */
	public double getDespesaFaturaValor() {
		return despesaFaturaValor;
	}

	/**
	 * @param despesaFaturaValor the despesaFaturaValor to set
	 */
	public void setDespesaFaturaValor(double despesaFaturaValor) {
		this.despesaFaturaValor = despesaFaturaValor;
	}
	
	/**
	 * @return the despesaValor
	 */
	public double getDespesaValor() {
		return despesaValor;
	}

	/**
	 * @param despesaValor the despesaValor to set
	 */
	public void setDespesaValor(double despesaValor) {
		this.despesaValor = despesaValor;
	}

	/**
	 * @return the iVAValor
	 */
	public double getIVAValor() {
		return this.IVAValor;
	}

	/**
	 * @param IVAValor the iVAValor to set
	 */
	public void setIVAValor(double IVAValor) {
		this.IVAValor = IVAValor;
	}
	
	/**
	 * @return the date 
	 */
	public LocalDate getDate() {
		return this.date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @param state Record state
	 */
	public void setState(RecordState state){
		this.state = state;
	}

	/**
	 * @return record state
	 */
	public RecordState getState(){
		return this.state;
	}
	@Override
	public boolean equals(Object o) {
		
		if(o == null) {
			return false;
		}
		
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Record)) {
			return false;
		}
		
		return this.date.isEqual(((Record) o).getDate());
	}

	@Override
	public int compareTo(Record o) {
		if(this.date.isAfter(o.getDate())) {
			return 1;
		}
		
		if(this.date.isBefore(o.getDate())) {
			return -1;
		}
		
		return 0;
	}

	@Override
	public String toString(){
		return this.date.toString() + ";" + String.valueOf(receitaDiariaValor) + ";" +
				String.valueOf(despesaFaturaValor) + ";" +
				String.valueOf(despesaValor) + ";" +
				String.valueOf(IVAValor);
	}
}
