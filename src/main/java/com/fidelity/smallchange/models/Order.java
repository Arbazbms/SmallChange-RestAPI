package com.fidelity.smallchange.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.fidelity.models.SellTrade;
//import com.fidelity.models.BuyTrade;



public class Order {
	public static final BigDecimal ZERO_VALUE = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
	
	private  String instrumentId;
	private  int quantity;
	private  BigDecimal targetPrice;
	private  String direction;
	private  String clientId;
	private  String orderId;
    private  String dateTime;
	
	
	public Order() {
		super();
	}


	public Order(String instrumentId, int quantity,BigDecimal targetPrice,String direction, String clientId, String orderId,String dateTime) {
               
		Objects.requireNonNull(direction,"direction can't be null");
		if (direction.length() == 0) {
			throw new IllegalArgumentException("direction can't be empty");
		}
		 Objects.requireNonNull(quantity,"quantity can't be null");
		if (quantity< 0) {
			throw new IllegalArgumentException("quantity must be greater than or equal to 0");
		}
		 Objects.requireNonNull(targetPrice,"targetPrice can't be null");
		if (targetPrice.compareTo(ZERO_VALUE) < 0) {
			throw new IllegalArgumentException("targetPrice must be greater than or equal to 0");
		}
        if (dateTime == null) {
			throw new NullPointerException("dateTime can't be null");
		}
		
		this.instrumentId = instrumentId;
		this.quantity = quantity;
		this.targetPrice = targetPrice.setScale(2, RoundingMode.HALF_EVEN);
		this.direction=direction;
		this.clientId=clientId;
		this.orderId=orderId;
		this.dateTime=dateTime;
               
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


	public BigDecimal getTargetPrice() {
		return targetPrice;
	}


	public void setTargetPrice(BigDecimal targetPrice) {
		this.targetPrice = targetPrice;
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


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public static BigDecimal getZeroValue() {
		return ZERO_VALUE;
	}


	@Override
	public int hashCode() {
		return Objects.hash(clientId, dateTime, direction, instrumentId, orderId, quantity, targetPrice);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(direction, other.direction) && Objects.equals(instrumentId, other.instrumentId)
				&& Objects.equals(orderId, other.orderId) && quantity == other.quantity
				&& Objects.equals(targetPrice, other.targetPrice);
	}


	@Override
	public String toString() {
		return "Order [instrumentId=" + instrumentId + ", quantity=" + quantity + ", targetPrice=" + targetPrice
				+ ", direction=" + direction + ", clientId=" + clientId + ", orderId=" + orderId + ", dateTime="
				+ dateTime + "]";
	}


	
	
//	public Trade executeTrade(BigDecimal executionPrice, List<Order> order) {
//		if(direction.equals("S")) {
//			Trade sell=new SellTrade(executionPrice, order);
//			return sell;
//		}else {
//		    Trade buy=new BuyTrade(executionPrice,order);
//		    return buy;
//		}
//	}

}
