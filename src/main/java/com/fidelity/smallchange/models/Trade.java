package com.fidelity.smallchange.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Trade {
	private  String instrumentId;
	private  BigDecimal quantity;
	private  BigDecimal executionPrice;
	private  String direction;
	private  String clientId;
	private  Order order;
	private  String tradeId;
	private  BigDecimal cashValue;
   public static final BigDecimal ZERO_VALUE = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
   
   
   
public Trade(String instrumentId, BigDecimal quantity, BigDecimal executionPrice, String direction, String clientId,
		Order order, String tradeId, BigDecimal cashValue) {
	super();
	Objects.requireNonNull(direction,"direction can't be null");
	if (direction.length() == 0) {
		throw new IllegalArgumentException("direction can't be empty");
	}
	 Objects.requireNonNull(quantity,"quantity can't be null");
	if (quantity.compareTo(ZERO_VALUE) < 0) {
		throw new IllegalArgumentException("quantity must be greater than or equal to 0");
	}
	 Objects.requireNonNull(executionPrice,"targetPrice can't be null");
	if (executionPrice.compareTo(ZERO_VALUE) < 0) {
		throw new IllegalArgumentException("targetPrice must be greater than or equal to 0");
	}
	this.instrumentId = instrumentId;
	this.quantity = quantity;
	this.executionPrice = executionPrice;
	this.direction = direction;
	this.clientId = clientId;
	this.order = order;
	this.tradeId = tradeId;
	this.cashValue = cashValue;
}

@Override
public String toString() {
	return "Trade [instrumentId=" + instrumentId + ", quantity=" + quantity + ", executionPrice=" + executionPrice
			+ ", direction=" + direction + ", clientId=" + clientId + ", order=" + order + ", tradeId=" + tradeId
			+ ", cashValue=" + cashValue + "]";
}

@Override
public int hashCode() {
	return Objects.hash(cashValue, clientId, direction, executionPrice, instrumentId, order, quantity, tradeId);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Trade other = (Trade) obj;
	return Objects.equals(cashValue, other.cashValue) && Objects.equals(clientId, other.clientId)
			&& Objects.equals(direction, other.direction) && Objects.equals(executionPrice, other.executionPrice)
			&& Objects.equals(instrumentId, other.instrumentId) && Objects.equals(order, other.order)
			&& Objects.equals(quantity, other.quantity) && Objects.equals(tradeId, other.tradeId);
}
	
//	private final List<Order> order;
//	private final BigDecimal executionPrice;
//    public Trade(BigDecimal executionPrice,List<Order> order) {
//    	if (executionPrice == null || executionPrice.compareTo(ZERO_VALUE) < 0) {
//			throw new IllegalArgumentException("executionPrice must be greater than or equal to 0");
//		}
//    	
//    	this.executionPrice=executionPrice;
//    	this.order=order;
//    }
//    public abstract void  placeTrade();
	
    
    
}
