package com.google.gwt.sample.stockwatcher.client;

public class StockPrice {

	/**
	 * Deklaration der Variablen.
	 */
	private String symbol;
	private double preis;
	private double änderung;
	
	/**
	 * Leerer Konstruktor, wird auch ohne anlegen beim ausführen der Applikation erzeugt.
	 * DefaultKonstruktor.
	 */
	public StockPrice(){
		
	}
	/**
	 * Setzen eines Konstruktors der Klasse StockPreis.
	 * @param symbol
	 * @param preis
	 * @param ändern
	 */
	public StockPrice(String symbol, double preis, double änderung){
		this.symbol = symbol;
		this.preis = preis;
		this.änderung = änderung;
	}
	
	
	
	//--------------Set-Methoden--------------------------
	/**
	 * Setzen einer Variable von Symbol
	 * @param symbol
	 */
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	/**
	 * Setzen einer Variable preis.
	 * @param preis
	 */
	public void setPreis(double preis){
		this.preis = preis;
	}
	/**
	 * Setzen einer Variable Änderung.
	 * @param ändern
	 */
	public void setÄndern(double änderung){
		this.änderung = änderung;
	}
	
	//------------------Get-Methoden------------------------
	/**
	 * Auslesen einer Variable Symbol.
	 * @return
	 */
	public String getSymbol(){
		return symbol;
	}
	/**
	 * Auslesen einer Variable Preis.
	 * @return
	 */
	public double getPreis(){
		return preis;
	}
	/**
	 * AUslesen einer Variable Änderung.
	 * @return
	 */
	public double getÄnderung(){
		return änderung;
	}
	
}
