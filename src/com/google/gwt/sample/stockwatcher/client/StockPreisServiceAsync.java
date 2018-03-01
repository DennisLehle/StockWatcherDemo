package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchrones Interface zum Synchronen StockPreisService Interface 
 * 
 * @author Dennis Lehle
 */
public interface StockPreisServiceAsync {

	void getPreis(String[] symbols, AsyncCallback<StockPrice[]> callback);

}
