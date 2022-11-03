package com.fidelity.smallchange.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fidelity.smallchange.models.Portfolio;
import com.fidelity.smallchange.models.PortfolioItem;

public interface PortfolioMapper {

	
	public List<PortfolioItem> getPortfolio(String id);
	public int addToPortfolio(PortfolioItem pi);
	public int updatePortfolioItem(PortfolioItem pi);
	public int removeFromPortfolio(String Portfolio_item_id);

}
