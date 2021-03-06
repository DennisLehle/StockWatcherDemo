package com.google.gwt.sample.stockwatcher.server;

import java.util.Random;

import com.google.gwt.sample.stockwatcher.client.DelistedException;
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
 * Zu throws DelistedExcpetion:
 * An diesem Punkt haben Sie den Code erstellt, der die Ausnahme auslöst. 
 * Sie müssen die throws-Deklaration nicht zur Service-Methode in StockPriceServiceAsync.java hinzufügen. 
 * Diese Methode kehrt immer sofort zurück (denken Sie daran, es ist asynchron). 
 * Stattdessen erhalten Sie alle ausgelösten aktivierten Ausnahmen, 
 *  wenn GWT die Callback-Methode onFailure (Throwable) aufruft.
 * 
 * @author Dennis Lehle
 *
 */
public class StockPreisServiceImpl extends RemoteServiceServlet implements StockPreisService{


	private static final double MAX_PRICE = 100.0; // $100.00
	private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%
	
	public StockPrice[] getPreis(String[] symbols) throws DelistedException {
		Random rand = new Random();
		
		StockPrice[] preise = new StockPrice[symbols.length];
		
		for(int i= 0; i<symbols.length; i++){
			if(symbols[i].equals("ERR")){
				throw new DelistedException("ERR");
		}
			double preis = rand.nextDouble()* MAX_PRICE;
			double änderung = preis * MAX_PRICE_CHANGE * (rand.nextDouble() * 2f - 1f);
			
			preise[i] = new StockPrice(symbols[i], preis, änderung);
		}
		
		return preise;
	}

}
