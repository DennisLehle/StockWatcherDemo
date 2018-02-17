package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;

/**
 * Imports die wichtig für den Stockwatcher sind.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcher implements EntryPoint {
	
	/**
	 * Alle wichtigen Panels, Button und Tabels um die Stockwatcher GUI aufzubauen.
	 */
	private static final int REFRESH_INTERVAL = 5000;
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable stocksFlexTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox newSymbolTextBox = new TextBox();
	private Button addStockButton = new Button("Add");
	private Label lastUpdatedLabel = new Label();
	private ArrayList<String> stocks = new ArrayList<String>();
	
	
	public void onModuleLoad() {
	
		/**
		 * Erst wird eine Tabelle für die Stock Daten erstellt.
		 */
		stocksFlexTable.setText(0, 0, "Symbol");
		stocksFlexTable.setText(0, 1, "Preis");
		stocksFlexTable.setText(0, 2, "Ändern");
		stocksFlexTable.setText(0, 3, "Löschen");
		
		/**
		 * Jetzt wird dem Horizontalen Panel die TextBox und der Stock Button angeheftet/montiert.
		 */
		addPanel.add(newSymbolTextBox);
		addPanel.add(addStockButton);
		
		/**
		 * Hier werden dem Vertikalen Panel den FlexTabel und das Label hinzugefügt plus 
		 * das addPanel, welches wir oben festgelegt haben auch an das mainPanel geheftet/verbunden.
		 */
		mainPanel.add(stocksFlexTable);
		mainPanel.add(addPanel);
		mainPanel.add(lastUpdatedLabel);
		
		/**
		 * Zuletzt wird das MainPanel, welches jetzt das AddPanel ebenfalls enthält zsmgefüht mit der HTML Hostpage.
		 */
		RootPanel.get("stockList").add(mainPanel);
		
		/**
		 * Wenn alles geladen wurde kann der User nun Stocks hinzufügen. Daher wird dies zuletzt angefügt.
		 * Hierfür wird die TextBox für die Maus auf true gesetzt.
		 */
		newSymbolTextBox.setFocus(true);
		
		/**
		 * Timer fürs automatische refreshing der Stocks.
		 */
	      Timer refreshTimer = new Timer() {
	        @Override
	        public void run() {
	        	refreshWatchList();
	        }
	      };
	      refreshTimer.scheduleRepeating(REFRESH_INTERVAL);


	
		
		/**
		 * ClickHandler wird realisiert in einer Annoynmen inneren Klasse, welche die Methode AddStock() aufruft,
		 * wenn der ClickHandler gefeuert wird.
		 */
		addStockButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				addStock();
			}
				
			});
	
		
		/**
		 * Methode wartet auf Eingaben in die TextBox
		 * Wenn etwas in die TextBox eingetragen wird, und Enter gedrückt wird wird die Methode addStock() ausgeführt.
		 */
		newSymbolTextBox.addKeyDownHandler(new KeyDownHandler(){
			public void onKeyDown(KeyDownEvent event){
				if (event.getNativeKeyCode()==KeyCodes.KEY_ENTER){
					addStock();
				}
			}
		});
	}
	
		/**
		 * Methode die einen Stock hinzufügt.
		 * Eingaben dürfen nur eine länge von 1-10 aufweisen.
	     * Erlaubte Eingaben sind: Punkt, Zahl und Buchstaben aber keine Sonderzeichen.
		 */
		private void addStock() {
	      final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
	      newSymbolTextBox.setFocus(true);

	      /**
	       * Wird ein Sonderzeichen eingegeben Popt ein Fenster auf, welches einen Hinweis gibt dass ein
	       * Sonderzeichen / ungültiges Zeichen eingegeben wurde.
	       */
	      if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
	        Window.alert("'" + symbol + "' is not a valid symbol.");
	        newSymbolTextBox.selectAll();
	        return;
	      }

	      /**
	       * Bei erfokgreicher Eingabe eines Stocks wird die TextBox wieder geleert.
	       */
	      newSymbolTextBox.setText("");
	      
	      /**
	       * Prüfung ob hinzuzufügendes Symbol schon vorhanden ist und wird bei vorhanden sein nicht übernommen.
	       * return Statement stoppt das weiter ausführen der Methode addStock().
	       */
	      if(stocks.contains(symbol))
	    	  return;
	      
	      /**
	       * Gibt die Anzahl der Rows zurück.
	       * Fügt den nicht vorhanden Stock zur Tabelle hinzu.
	       */
	      int row = stocksFlexTable.getRowCount();
	      stocks.add(symbol);
	      /**
	       * Andekdote:
	       * Wird die Methode setText() aufgerufen wird sich der FlexTabel automatisch anpassen.
	       * Daher muss man sich nicht um die Anpassung der Tabelle kümmern.
	       */
	      stocksFlexTable.setText(row, 0, symbol);
	      
	      /**
	       * Ein Button fürs löschen wird erzeugt.
	       */
	      Button löscheStockButton = new Button ("löschen");
	      
	      /**
	       * Dem löschenStockButton wird ein ClickHandler zugewiesen der auf einen Click auf den Button wartet.
	       */
	      löscheStockButton.addClickHandler(new ClickHandler(){
	    	  
			public void onClick(ClickEvent event) {
				int löschIndex = stocks.indexOf(symbol);
				stocks.remove(löschIndex);
				stocksFlexTable.removeRow(löschIndex + 1);	
			}
	      });
	      stocksFlexTable.setWidget(row, 3, löscheStockButton);
	      
	      // TODO Get the stock price.
	      refreshWatchList();
	

	    }
		
		/**
		 * Methode zum refreshen der WatchList.
		 */
		private void refreshWatchList(){
			
		}
	}

