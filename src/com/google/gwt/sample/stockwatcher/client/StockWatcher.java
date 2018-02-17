package com.google.gwt.sample.stockwatcher.client;

/**
 * Imports die wichtig für den Stockwatcher sind.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		
	}
}
