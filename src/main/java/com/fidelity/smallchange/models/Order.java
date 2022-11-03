package com.fidelity.smallchange.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
//import com.fidelity.models.SellTrade;
//import com.fidelity.models.BuyTrade;
public class Order {
	public static final BigDecimal ZERO_VALUE = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
	
	private  String instrumentId;
	private  BigDecimal quantity;
	private  BigDecimal targetPrice;
	private  String direction;
	private  String clientId;
	private  String orderId;
    private  LocalDateTime dateTime;
	
	
	public Order(String instrumentId, BigDecimal quantity,BigDecimal targetPrice,String direction, String clientId, String orderId,LocalDateTime dateTime) {
               
		Objects.requireNonNull(direction,"direction can't be null");
		if (direction.length() == 0) {
			throw new IllegalArgumentException("direction can't be empty");
		}
		 Objects.requireNonNull(quantity,"quantity can't be null");
		if (quantity.compareTo(ZERO_VALUE) < 0) {
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
		this.quantity = quantity.setScale(2, RoundingMode.HALF_EVEN);
		this.targetPrice = targetPrice.setScale(2, RoundingMode.HALF_EVEN);
		this.direction=direction;
		this.clientId=clientId;
		this.orderId=orderId;
		this.dateTime=dateTime;
               
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
