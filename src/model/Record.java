package model;

import java.util.Calendar;

import util.DateTime;

public class Record implements Comparable<Record>{

	private double receitaDiariaValor;
	private double despesaFaturaValor;
	private double despesaValor;
	private double IVAValor;
	private Calendar date;
	
	public Record(double receitaDiariaValor, double despesaFaturaValor, double despesaValor, double IVAValor, Calendar date) {
		this.setReceitaDiariaValor(receitaDiariaValor);
		this.setDespesaFaturaValor(despesaFaturaValor);
		this.setDespesaValor(despesaValor);
		this.setIVAValor(IVAValor);
		this.setDate(date);
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
	 * @param iVAValor the iVAValor to set
	 */
	public void setIVAValor(double IVAValor) {
		this.IVAValor = IVAValor;
	}
	
	/**
	 * @return the date 
	 */
	public Calendar getDate() {
		return this.date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
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
		
		return DateTime.compareDates(this.date, ((Record)o).getDate());
	}

	@Override
	public int compareTo(Record o) {
		if(this.date.after(o.getDate())) {
			return 1;
		}
		
		if(this.date.before(o.getDate())) {
			return -1;
		}
		
		return 0;
	}
}
