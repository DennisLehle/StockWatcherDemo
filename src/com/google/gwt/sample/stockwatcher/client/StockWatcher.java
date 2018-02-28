package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.Date;

/**
 * Imports die wichtig für den Stockwatcher sind.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcher implements EntryPoint {
	
	
	/**
	 * Experiment, ich lege Buttons und ein VertricalPanel an die den StockWatcher aufrufen werden.
	 */
	private Button aufrufStockWatcher = new Button("Open StockWatcher");
	private VerticalPanel hauptPanel = new VerticalPanel();
	
	/**
	 * Ist der Ursprung allen übels. 
	 * Her fängt das Programm an zu starten.
	 */
	public void onModuleLoad() {
		
		hauptPanel.add(aufrufStockWatcher);
		aufrufStockWatcher.setStyleName("center");
		hauptPanel.setStyleName("center");
		
		RootPanel.get().add(hauptPanel);
		
		/**
		 * ClickHandler wird realisiert in einer Annoynmen inneren Klasse, welche die Methode OnLoad() Methode 
		 * in der Klasse StockWatcher aufruft.
		 * Der Button wird gleichzeitig entfernt wenn wir die onLoad Methode aufrufen, damit nicht unendlich viele 
		 * StockWatcher erstellt werden können.
		 */
		aufrufStockWatcher.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				StartStockWatcher sw = new StartStockWatcher();
				sw.onLoad();
				aufrufStockWatcher.removeFromParent();
		
			}
				
			});
	
	
	}
}
