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
				Methods.getDataForTable(Globals.STOCKS), 
				Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
	}
	
	//Overridden Methods
	@Override
	public void refresh() 
	{
		this.getOverviewTablePanel().refresh(Methods.getDataForTable(Globals.STOCKS), 
												Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.ITEM_NAME, Globals.STOCKS.toArray(new Stock[Globals.STOCKS.size()]));
	}

}
