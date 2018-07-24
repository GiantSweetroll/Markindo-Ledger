package gui.overviewpanel;

import constants.Constants;
import constants.FramePanelConstants;
import gui.methods.FileOperation;
import gui.methods.Methods;

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
				Methods.getDataForTable(FileOperation.loadStock()), 
				Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
	}
	
	//Overridden Methods
	@Override
	public void refresh() 
	{
		this.getOverviewTablePanel().refresh(Methods.getDataForTable(FileOperation.loadStock()), 
												Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
	}

}
