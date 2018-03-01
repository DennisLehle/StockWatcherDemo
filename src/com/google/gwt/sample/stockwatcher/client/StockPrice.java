package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class StockPrice implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * Deklaration der Variablen.
	 */
	private String symbol;
	private double preis;
	private double aenderung;
	
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
	public StockPrice(String symbol, double preis, double aenderung){
		this.symbol = symbol;
		this.preis = preis;
		this.aenderung = aenderung;
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
	public void setAendern(double aenderung){
		this.aenderung = aenderung;
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
	public double getAenderung(){
		return aenderung;
	}
	
	public double getAenderungProzent(){
		return 100.0 * this.aenderung / this.preis;
	}
	
}
