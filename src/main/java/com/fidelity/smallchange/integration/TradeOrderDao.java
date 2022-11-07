package com.fidelity.smallchange.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.smallchange.integration.mapper.PreferenceMapper;
import com.fidelity.smallchange.integration.mapper.TradeOrderMapper;
import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;

@Repository("tradeOrderDao")
public class TradeOrderDao {
	@Autowired
	private TradeOrderMapper mapper;
	
	public int insertTrade(Trade trade) {
		return mapper.insertTrade(trade);
	}
	public int insertOrder(Order order) {
		return mapper.insertOrder(order);
	}
	public List<Trade> queryTradeByClientId(String clientId){
		//trade history
		return mapper.queryTradeByClientId(clientId);
	}
	public List<Trade> queryTradeByTradeId(String tradeId){
		return mapper.queryTradeByTradeId(tradeId);
	}
	public List<Trade> queryTradeByOrderId(String orderId){
		return mapper.queryTradeByOrderId(orderId);
	}
}
