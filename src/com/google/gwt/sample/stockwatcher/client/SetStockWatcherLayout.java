package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Mit dieser Klasse wird ein Layout angepasst (Google-Img).
 * Man könnte dieses Image auch einzeln in die Methode onLoad() hinein schreiben, zu Test zwecken 
 * wurde dies aber hier realisiert.
 * @author Dennis Lehle
 *
 */
public class SetStockWatcherLayout implements Serializable{
	
	public HorizontalPanel swLayout = new HorizontalPanel();
	
	
	public void setLayout(){

		swLayout.add(new HTML("<img width=\"50%\" src=\"images/Google-Code.jpg\" alt=\"Fehler\">"));
		swLayout.addStyleName("centerImg");

	}

}
