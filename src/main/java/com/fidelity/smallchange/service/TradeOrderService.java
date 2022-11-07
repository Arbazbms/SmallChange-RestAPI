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
	
	public int insertTrade(Trade trade) {
		return dao.insertTrade(trade);
	}
	public int insertOrder(Order order) {
		return dao.insertOrder(order);
	}
	public List<Trade> queryTradeByClientId(String clientId){
		//trade history
		return dao.queryTradeByClientId(clientId);
	}
	public List<Trade> queryTradeByTradeId(String tradeId){
		return dao.queryTradeByTradeId(tradeId);
	}
	public List<Trade> queryTradeByOrderId(String orderId){
		return dao.queryTradeByOrderId(orderId);
	}
	
	
}
