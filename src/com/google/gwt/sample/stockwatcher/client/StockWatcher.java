package com.google.gwt.sample.stockwatcher.client;

/**
 * Imports die wichtig für den Stockwatcher sind.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
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
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable stocksFelxTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox newSymbolTextBox = new TextBox();
	private Button addStockButton = new Button("Add");
	private Label lastUpdatedLabel = new Label();
	
	
	public void onModuleLoad() {
	
		/**
		 * Erst wird eine Tabelle für die Stock Daten erstellt.
		 */
		stocksFelxTable.setText(0, 0, "Symbol");
		stocksFelxTable.setText(0, 1, "Preis");
		stocksFelxTable.setText(0, 2, "Ändern");
		stocksFelxTable.setText(0, 3, "Löschen");
		
		/**
		 * Jetzt wird dem Horizontalen Panel die TextBox und der Stock Button angeheftet/montiert.
		 */
		addPanel.add(newSymbolTextBox);
		addPanel.add(addStockButton);
		
		/**
		 * Hier werden dem Vertikalen Panel den FlexTabel und das Label hinzugefügt plus 
		 * das addPanel, welches wir oben festgelegt haben auch an das mainPanel geheftet/verbunden.
		 */
		mainPanel.add(stocksFelxTable);
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

	    }
		
	}

