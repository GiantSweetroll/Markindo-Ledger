package gui.pengiriman;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import gui.overviewpanel.OverviewPanel;
import gui.search.SearchFilterItem;
import methods.Methods;

public class PengirimanOverview extends OverviewPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 337051624508517964L;


	public PengirimanOverview()
	{
		super("Pengiriman", 
				FramePanelConstants.PENGIRIMAN_INPUT,
				Globals.PENGIRIMAN, 
				Methods.createTableHeaderWithActionCell(Constants.PENGIRIMAN_TABLE_HEADERS));
		
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Program", SearchFilterItem.PROGRAM, Globals.PROGRAMS.toArray(new Program[Globals.PROGRAMS.size()])));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Area", SearchFilterItem.AREA, Methods.getListOfAreas()));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Site", SearchFilterItem.SITE, Globals.SITES.toArray(new Site[Globals.SITES.size()])));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Pengirim", SearchFilterItem.SENDER, Constants.SENDERS));
	}
	
	
	//Overridden Methods
	@Override
	public void refresh()
	{
		this.getOverviewTablePanel().refresh(Methods.filterPengiriman(), 
				Methods.createTableHeaderWithActionCell(Constants.PENGIRIMAN_TABLE_HEADERS));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.STOCK, Globals.STOCKS.toArray(new Stock[Globals.STOCKS.size()]));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.AREA, Methods.getListOfAreas());
		this.getSearchFilterPanel().updateItem(SearchFilterItem.PROGRAM, Globals.PROGRAMS.toArray(new Program[Globals.PROGRAMS.size()]));
	}


	@Override
	public void filter() 
	{
		this.getOverviewTablePanel().refresh(Methods.filterPengiriman(), 
				Methods.createTableHeaderWithActionCell(Constants.PENGIRIMAN_TABLE_HEADERS));
	}
}
