package model;

public class Registo {

	private double receitaDiariaValor;
	private double despesaFaturaValor;
	private double despesaValor;
	private double IVAValor;
	
	public Registo(double receitaDiariaValor, double despesaFaturaValor, double despesaValor, double IVAValor) {
		this.setReceitaDiariaValor(receitaDiariaValor);
		this.setDespesaFaturaValor(despesaFaturaValor);
		this.setDespesaValor(despesaValor);
		this.setIVAValor(IVAValor);
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
}
