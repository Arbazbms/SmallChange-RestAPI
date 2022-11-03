package com.fidelity.smallchange.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {
	private String instrumentId;
	private BigDecimal bidPrice;
	private BigDecimal askPrice;
	private LocalDateTime timeStamp;
	private Instrument instrument;
	
	
	
	public Price(String instrumentId, BigDecimal bidPrice, BigDecimal askPrice, LocalDateTime timeStamp) {
		super();
		this.instrumentId = instrumentId;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.timeStamp = timeStamp;
	}

	public String getInstrumentId() {
		return instrumentId;
	}
	
	public BigDecimal getBidPrice() {
		return bidPrice;
	}
	
	public BigDecimal getAskPrice() {
		return askPrice;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	public Instrument getInstrument() {
		return instrument;
	}
	
	

}
