package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.Date;

/**
 * Imports die wichtig für den Stockwatcher sind.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		
		/**
		 * Erstellung des Proxys, welche bei einem CallBack vom Server ein Callback Objekt bekommt.
		 * Benutzt wird das Asynchone Interface.
		 */
		private StockPreisServiceAsync stockPService = GWT.create(StockPreisService.class);
		    
		
		/**
		 * Diese Methode wird geladen wenn der Button auf der Hauptseite des Stockwatchers gedrückt wurde.
		 */
		public void onModuleLoad()  {
			
		/**
		 * Es wird als erstes das vordefinierte Layout des Stockwatchers ausgerufen.
		 * Dafür wird ein Objekt der Klasse SetStockWatcherLayout erstellt und darüber wird die Methode
		 * setLayout aufgerufen.
		 * 
		 * @see SetStockWatcherLayout 
		 */
		SetStockWatcherLayout layout = new SetStockWatcherLayout();
		layout.setLayout();
		
		/**
		 * Erst wird eine Tabelle für die Stock Daten erstellt.
		 */
		stocksFlexTable.setText(0, 0, "Symbol");
		stocksFlexTable.setText(0, 1, "Preis");
		stocksFlexTable.setText(0, 2, "Ändern");
		stocksFlexTable.setText(0, 3, "Löschen");
		
		/**
		 * Gehört zu: Hinzufügen von Style elemente in die StockList Tabelle.
		 * CellPadding wird für das Auffüllen der Zellen genutzt.
		 */
		stocksFlexTable.setCellPadding(6);
		
		/**
		 * Hinzufügen von Style elemente in die StockList Tabelle.
		 * Die Column ab dem 3ten stocksFlexTabble.getCellFormatter.addStyleName spiegeln
		 * die Column wider die wir bei den Buttons oben festgelegt haben.
		 */
		stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
		stocksFlexTable.addStyleName("watchList");
		stocksFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
		
		/**
		 * Jetzt wird dem Horizontalen Panel die TextBox und der Stock Button angeheftet/montiert.
		 */
		addPanel.add(newSymbolTextBox);
		addPanel.add(addStockButton);
		/**
		 * Style für das addPanel von CSS einbinden.
		 * Fügt eine kleine Lücke zwischen TexBox/AddButton und dem Stock Table ein.
		 */
		addPanel.addStyleName("hinzufügePanel");
		
		/**
		 * Hier werden dem Vertikalen Panel den FlexTabel und das Label hinzugefügt plus 
		 * das addPanel, welches wir oben festgelegt haben auch an das mainPanel geheftet/verbunden.
		 * 
		 * Dem MainPanel wird noch das swLayout hinzugefügt, welches das Google Image montiert..
		 */
		mainPanel.add(layout.swLayout);
		mainPanel.add(stocksFlexTable);
		mainPanel.add(addPanel);
		mainPanel.add(lastUpdatedLabel);

		
		/**
		 * Zuletzt wird das MainPanel, welches jetzt das AddPanel ebenfalls enthält zsmgefüht mit der HTML Hostpage.
		 */
		RootPanel.get("stockList").add(mainPanel);
		RootPanel.get().setStyleName("center");
			
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
	       * Hier wird der Style für den hinzugefügten Stock definiert, wenn der User einen Stock hinzufügt
	       */
	      stocksFlexTable.setText(row, 0, symbol);
	      /**
	       * Hier wird ein Label Widget erstellt für jede Zelle in Column 2.
	       * 
	       */
	      stocksFlexTable.setWidget(row, 2, new Label());
	      stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
	      stocksFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
	      stocksFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
	      
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
	       * CSS Style zum löschen Button hinzufügen.
	       * Das Wort löschen wird in der CSS definiert. nicht wie der Button
	       * oben genannt wurde.
	       */
	      löscheStockButton.addStyleDependentName("löschen");
	      
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
		 * Wird aufgerufen wenn User auf Add Stock drückt und alle 5 Sekunden wenn der Timer feuert.
		 * 
		 * Jetzt haben wir diese Methode ins Server Package geschoben und einen RPC call draufgepackt.
		 */
		private void refreshWatchList(){
			//Inizialisieren des Proxy Objektes.
			//Erstelltes Objekt wird aufgerufen und geprüft ob es existiert, wenn nicht wird eins erstellt.
			if(stockPService == null){
				stockPService = GWT.create(StockPreisService.class);
			}
			
			//Setzen des Callback Objekts.
			AsyncCallback<StockPrice[]> callback = new AsyncCallback<StockPrice[]>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(StockPrice[] result) {
					updateTable(result);
					
				}		
			};
			
			// Mache den Call zum Stock Preis Service.
			stockPService.getPreis(stocks.toArray(new String[0]), callback);
		}
			
	/**
	 * Dieser Methoen Inhalt wird jetzt durch die Mehthode ersetzt die wir im Server Package geschrieben haben.
	 * Es wird das proxy Objekt von Oben aufgerufen, weclhes die Methode auf dem Server aufruft (refreshWatchList()).		
	 */
//			//Anlegen von Lokalen Variablen.
//			final double MAX_PRICE = 100.0; // $100.00
//			final double MAX_PRICE_CHANGE = 0.02; //+/- 2%
//			
//			/**
//			 * 1. Neues ArrayList Objekt namens Stockpreise wird erstellt. [länge der Stocks die vorhanden sind. ArrayListe oben definiert]
//			 * 2. Die ArrayListe stocks wird durchlaufen (for-Schleife)
//			 * 3. In der for-Schleife werden die Variablen mit hilfe der Random Klasse berechnet. Mit den Werten die in den Variablen ganz oben festgelegt wurden.
//			 * 4. Die Werte werden dann der updateTable() Methode weitergereicht.
//			 */
//			StockPrice[] preise = new StockPrice[stocks.size()];
//			for(int i = 0; i < stocks.size(); i++){
//				double preis = Random.nextDouble() * MAX_PRICE;
//				double änderung = preis * MAX_PRICE_CHANGE * (Random.nextDouble() * 2.0 - 1.0);
//				
//			preise[i] = new StockPrice(stocks.get(i), preis, änderung);
//			
//			}			
//			updateTable(preise);
//		}
		
		/**
		 * Methode zum Updaten der Preise einer Tabelle.
		 * @param preise
		 */
		private void updateTable (StockPrice[] preise){
			for (int i = 0; i < preise.length; i++){
				updateTable(preise[i]);
			}
			/**
			 * Anzeigen des Zeitstempels und letzter Refresh.
			 */
			DateTimeFormat datumsFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
			lastUpdatedLabel.setText("Letztes update : " + datumsFormat.format(new Date()));

		}

		/**
		 * Updaten einer einzelnen Zeile der Stock Tabelle.
		 * @param stockPrice
		 */
		private void updateTable(StockPrice preis) {
			/**
			 * Abfrage ob Stock sich noch in der Tabelle befindet.
			 * Wenn nicht wird die Methode beendet.
			 */
			if(!stocks.contains(preis.getSymbol())){
				return;
			}
			
			int row = stocks.indexOf(preis.getSymbol()) + 1;
			
			String preisText = NumberFormat.getFormat("#,##0.00").format(preis.getPreis());
			NumberFormat aendernFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
			String aenderungText = aendernFormat.format(preis.getAenderung());
		    String aenderungProzentText = aendernFormat.format(preis.getAenderungProzent());
		    
		    /**
		     * Ausfüllen der Felder und Preis (Aktualisierung).
		     */
		    stocksFlexTable.setText(row, 1, preisText);
		    Label aenderungWidget = (Label)stocksFlexTable.getWidget(row, 2);
		    aenderungWidget.setText(aenderungText + " (" + aenderungProzentText + "%)");
		    
		    /**
		     * Farbliche Änderung im Feld "Änderung" bei Positiven und Negativen Beiträgen.
		     */
		    String aendereStyleName = "keinChange";
		    if (preis.getAenderungProzent() < -0.1f){
		    	aendereStyleName = "negativeÄnderung";
		    }
		    else if (preis.getAenderungProzent() > 0.1f){
		    	aendereStyleName = "positiveÄnderung";
		    }
		    
		    aenderungWidget.setStyleName(aendereStyleName);
		    
		    /**
		     * Wird für CSS ausgeblendet, weil man dies Anfangs so programmieren soll, wenn man
		     * noch keine farblichen Änderungen mit einbinden will bei Änderunge von positiven/negativen Beiträgen.
		     */
		   // stocksFlexTable.setText(row, 2, aenderungText + " (" + aenderungProzentText + "%)");
		}


}
