package com.fidelity.smallchange.models;

import java.util.Objects;
import java.math.BigDecimal;

public class PortfolioItem {
	
	@Override
	public String toString() {
		return "PortfolioItem [portfolio_item_id=" + portfolio_item_id + ", client_id=" + client_id + ", instrument_id="
				+ instrument_id + ", trade_id=" + trade_id + ", quantity=" + quantity + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(client_id, instrument_id, portfolio_item_id, trade_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortfolioItem other = (PortfolioItem) obj;
		return Objects.equals(client_id, other.client_id) && Objects.equals(instrument_id, other.instrument_id)
				&& Objects.equals(portfolio_item_id, other.portfolio_item_id);
	}
	private String portfolio_item_id;
	private String client_id;
	private String instrument_id;
	private String trade_id;
	private BigDecimal cost_price;
	private int quantity;
	public PortfolioItem() {}
	public PortfolioItem(String client_id,String portfolio_item_id, String instrument_id, String trade_id,BigDecimal cost_price,int quantity) {
		this.client_id=client_id;
		this.portfolio_item_id = portfolio_item_id;
		this.instrument_id = instrument_id;
		this.trade_id = trade_id;
		this.cost_price=cost_price;
		this.quantity=quantity;
	}
	public BigDecimal getCost_price() {
		return cost_price;
	}
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	public String getClient_id() {
		return client_id;
	}
	public String getPortfolio_item_id() {
		return portfolio_item_id;
	}
	public String getInstrument_id() {
		return instrument_id;
	}
	public String getTrade_id() {
		return trade_id;
	}
	public void  updateQuantity(int quantity) {
		this.quantity=quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setPortfolio_item_id(String portfolio_item_id) {
		this.portfolio_item_id = portfolio_item_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public void setInstrument_id(String instrument_id) {
		this.instrument_id = instrument_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
