package com.fidelity.smallchange.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;

@Mapper
public interface TradeOrderMapper {
	public int insertTrade(@Param("trade")Trade trade);
	public int insertOrder(@Param("order")Order order);
	public List<Trade> queryTradeByClientId(String clientId);//trade history
	public List<Trade> queryTradeByTradeId(String tradeId);
	public List<Trade> queryTradeByOrderId(String orderId);
}
