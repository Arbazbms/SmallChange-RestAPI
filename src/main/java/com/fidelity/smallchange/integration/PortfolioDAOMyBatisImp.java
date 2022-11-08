package com.fidelity.smallchange.integration;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.smallchange.integration.mapper.PortfolioMapper;
import com.fidelity.smallchange.models.Portfolio;
import com.fidelity.smallchange.models.PortfolioItem;
@Repository("PortfolioDAO")
public class PortfolioDAOMyBatisImp {
	
	@Autowired
	private Logger logger;

	@Autowired
	private PortfolioMapper mapper;
	
	
	public List<PortfolioItem> getPortfolio(String id)
	{
		List<PortfolioItem> result=mapper.getPortfolio(id);
		return result;
	}
	
	public boolean addToPortfolio(PortfolioItem pi) {
		
		return mapper.addToPortfolio(pi)==1;
	}
	public boolean updatePortfolioItem(PortfolioItem pi) {
		return mapper.updatePortfolioItem(pi)==1;
	}
	public boolean removeFromPortfolio(String Portfolio_item_id) {
		 return mapper.removeFromPortfolio(Portfolio_item_id)==1;
	}

}
