package com.fidelity.smallchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.smallchange.integration.PortfolioDAOMyBatisImp;
import com.fidelity.smallchange.integration.mapper.PortfolioMapper;
import com.fidelity.smallchange.models.PortfolioItem;
import java.util.List;
@Service()
public class PortfolioBusinessService {
	
	@Autowired
	PortfolioDAOMyBatisImp dao;
	
	public List<PortfolioItem> getPortfolio(String client_id){
		List<PortfolioItem> client_portfolio=dao.getPortfolio(client_id);
		return client_portfolio;
	}
	public boolean addToPortfolio(PortfolioItem pi) {
		
		return dao.addToPortfolio(pi);
	}
	public boolean updatePortfolioItem(PortfolioItem pi) {
		return dao.updatePortfolioItem(pi);
	}
	public boolean removeFromPortfolio(String Portfolio_item_id) {
		 return dao.removeFromPortfolio(Portfolio_item_id);
	}


}
