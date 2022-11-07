package com.fidelity.smallchange.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;




public class Trade {
	private  String instrumentId;
	private  int quantity;
	private  BigDecimal executionPrice;
	private  String direction;
	private  String clientId;
	private  String tradeId;
	private  BigDecimal cashValue;
	private String orderId;
	
	private  Order order;

   public static final BigDecimal ZERO_VALUE = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
   
 
   
   
public Trade(String instrumentId, int quantity, BigDecimal executionPrice, String direction, String clientId,
		String orderId, String tradeId, BigDecimal cashValue) {
	super();
	Objects.requireNonNull(direction,"direction can't be null");
	if (direction.length() == 0) {
		throw new IllegalArgumentException("direction can't be empty");
	}
	 Objects.requireNonNull(quantity,"quantity can't be null");
	if (quantity < 0) {
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
	this.orderId = orderId;
	this.tradeId = tradeId;
	this.cashValue = cashValue;
}



public Trade(String instrumentId, int quantity, BigDecimal executionPrice, String direction, String clientId,
		String orderId, String tradeId,BigDecimal cashValue,BigDecimal targetPrice,LocalDateTime dateTime) {
	this(instrumentId,quantity,executionPrice,direction,clientId,orderId,tradeId,cashValue);
	this.order=new Order(instrumentId,quantity,targetPrice,direction,clientId,orderId,dateTime);
}

public Trade() {
	super();
}



@Override
public int hashCode() {
	return Objects.hash(cashValue, clientId, direction, executionPrice, instrumentId, order, orderId, quantity,
			tradeId);
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
			&& Objects.equals(orderId, other.orderId) && quantity == other.quantity
			&& Objects.equals(tradeId, other.tradeId);
}



@Override
public String toString() {
	return "Trade [instrumentId=" + instrumentId + ", quantity=" + quantity + ", executionPrice=" + executionPrice
			+ ", direction=" + direction + ", clientId=" + clientId + ", tradeId=" + tradeId + ", cashValue="
			+ cashValue + ", orderId=" + orderId + ", order=" + order + "]";
}



public String getInstrumentId() {
	return instrumentId;
}



public void setInstrumentId(String instrumentId) {
	this.instrumentId = instrumentId;
}



public int getQuantity() {
	return quantity;
}



public void setQuantity(int quantity) {
	this.quantity = quantity;
}



public BigDecimal getExecutionPrice() {
	return executionPrice;
}



public void setExecutionPrice(BigDecimal executionPrice) {
	this.executionPrice = executionPrice;
}



public String getDirection() {
	return direction;
}



public void setDirection(String direction) {
	this.direction = direction;
}



public String getClientId() {
	return clientId;
}



public void setClientId(String clientId) {
	this.clientId = clientId;
}



public String getTradeId() {
	return tradeId;
}



public void setTradeId(String tradeId) {
	this.tradeId = tradeId;
}



public BigDecimal getCashValue() {
	return cashValue;
}



public void setCashValue(BigDecimal cashValue) {
	this.cashValue = cashValue;
}



public String getOrderId() {
	return orderId;
}



public void setOrderId(String orderId) {
	this.orderId = orderId;
}



public Order getOrder() {
	return order;
}



public void setOrder(Order order) {
	this.order = order;
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
