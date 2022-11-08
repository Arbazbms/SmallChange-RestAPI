package com.fidelity.smallchange.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.smallchange.integration.TradeOrderDao;
import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;



@Service()

public class TradeOrderService {
	@Autowired
	private TradeOrderDao dao;
	
//	public int insertTrade(Trade trade) {
//		return dao.insertTrade(trade);
//	}
	public List<Trade> queryTradeByOrderId(String orderId){
		return dao.queryTradeByOrderId(orderId);
	}
	public List<Trade> insertOrder(Order order,Trade t) {
		//int count = 0;
		try {
			dao.insertOrder(order);
			dao.insertTrade(t);
		} catch (Exception e) {
			String msg = "Error inserting order";
			throw new DatabaseException(msg, e);
		}
		return queryTradeByOrderId(order.getOrderId());
		//return dao.insertOrder(order);
	}
	public List<Trade> queryTradeByClientId(String clientId){
		//trade history
		return dao.queryTradeByClientId(clientId);
	}
	public List<Trade> queryTradeByTradeId(String tradeId){
		return dao.queryTradeByTradeId(tradeId);
	}
	
	
	
}
