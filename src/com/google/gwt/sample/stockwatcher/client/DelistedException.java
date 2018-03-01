package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DelistedException extends Exception implements IsSerializable{
	private static final long serialVersionUID = 1L;

	 private String symbol;

	  public DelistedException() {
	  }

	  public DelistedException(String symbol) {
	    this.symbol = symbol;
	  }

	  public String getSymbol() {
	    return this.symbol;
	  }
	}
