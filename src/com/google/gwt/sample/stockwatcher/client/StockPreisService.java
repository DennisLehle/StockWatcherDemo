package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Client-side Code für den RPC Service.
 * @author Dennis Lehle
 *
 */
@RemoteServiceRelativePath("stockPrice")
public interface StockPreisService extends RemoteService{

	StockPrice[] getPreis(String[] symbols) throws DelistedException;
	
}
