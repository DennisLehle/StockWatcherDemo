package com.google.gwt.sample.stockwatcher.server;

import java.util.Random;

import com.google.gwt.sample.stockwatcher.client.StockPreisService;
import com.google.gwt.sample.stockwatcher.client.StockPrice;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Server-seitige implementierung von Methoden die durch die Client-seite aufgerufen werden.
 * RemoteServiceServlet übernimmt den Part der Serialisierung und Deserialisierung von Objekten die 
 * zwischen Client und Server hin und her geschickt werden.
 * Geschiet im Text Format.
 * 
 * Die Service Implementierung läuft auf dem Server als Java bytecode und wird nicht in JavaScript übersetzt.
 * Für Trennung zwischen Client und Server seite wird ein extra Package mit der Endung .server angelegt.
 * 3 Tier Model - Bessere Wartung.
 * 
 * Erinnerung:
 * Man kann hier auch Java Bibliotheken nutzen da diese Methoden auf dem Server laufen (Java Bytecode) 
 * und nicht in JavaScript übersetzt werden.
 * 
 * @author Dennis Lehle
 *
 */
public class StockPreisServiceImpl extends RemoteServiceServlet implements StockPreisService{

	private static final double MAX_PRICE = 100.0; // $100.00
	private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%
	
	public StockPrice[] getPreis(String[] symbols) {
		Random rand = new Random();
		
		StockPrice[] preise = new StockPrice[symbols.length];
		for(int i= 0; i<symbols.length; i++){
			double preis = rand.nextDouble()* MAX_PRICE;
			double änderung = preis * MAX_PRICE_CHANGE * (rand.nextDouble() * 2f - 1f);
			
			preise[i] = new StockPrice(symbols[i], preis, änderung);
		}
		
		return preise;
	}
	

	

}
