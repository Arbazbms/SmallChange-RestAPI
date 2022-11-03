package com.fidelity.smallchange.models;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	private String clientid;
	private List<PortfolioItem> item_list=new ArrayList<PortfolioItem>();
	public Portfolio(String clientid) {
		super();
		this.clientid = clientid;
	}
	public void addToPortfolio(PortfolioItem item) {
		if(item==null)
			throw new IllegalArgumentException("null reference passed");
		item_list.add(item);
	}
	public void removeAllFromPortfolio(String portfolio_item_id) {
		if(portfolio_item_id==null||portfolio_item_id=="")
			throw new IllegalArgumentException("null or empty reference passed");
		for(PortfolioItem pi: item_list) {
			if(pi.getPortfolio_item_id().equals(portfolio_item_id))
			item_list.remove(pi);
			return;
		}
		throw new IllegalArgumentException("no match found");
		
	}
	public void removeSomeFromPortfolio(String portfolio_item_id,int new_quantity) {
		for(PortfolioItem pi: item_list) {
			if(pi.getPortfolio_item_id().equals(portfolio_item_id))
			pi.updateQuantity(new_quantity);
		}
		
	}
	public PortfolioItem getPortfolioItembyId(String portfolio_item_id ) {
		for(PortfolioItem pi: item_list) {
			if(pi.getPortfolio_item_id().equals(portfolio_item_id))
			return pi;
		}
		throw new IllegalArgumentException("No matching item found for id provided");
	}
	
}
