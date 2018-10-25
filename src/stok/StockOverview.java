package stok;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Stock;
import gui.overviewpanel.OverviewPanel;
import gui.search.SearchFilterItem;
import methods.Methods;

public class StockOverview extends OverviewPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3792339165691238959L;
	
	public StockOverview()
	{
		super("Stok", 
				FramePanelConstants.STOCK_INPUT,
				Globals.STOCKS, 
				Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
	}
	
	//Overridden Methods
	@Override
	public void refresh() 
	{
		this.getOverviewTablePanel().refresh(Methods.filterStock(), 
												Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.STOCK, Globals.STOCKS.toArray(new Stock[Globals.STOCKS.size()]));
	}

	@Override
	public void filter() 
	{
		this.getOverviewTablePanel().refresh(Methods.filterStock(), 
				Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
	}
}
