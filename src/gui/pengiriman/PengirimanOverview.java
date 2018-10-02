package gui.pengiriman;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
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
				Methods.getDataForTable(Globals.ALOKASI), 
				Methods.createTableHeaderWithActionCell(Constants.PENGIRIMAN_TABLE_HEADERS));
		
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Program", SearchFilterItem.PROGRAM, Methods.getDisplayNames(Globals.PROGRAMS)));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Area", SearchFilterItem.AREA, new String[1]));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Site", SearchFilterItem.SITE, Methods.getDisplayNames(Globals.SITES)));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("PIC", SearchFilterItem.PIC, Methods.getDisplayNames(Globals.PICS)));
	}
	
	
	//Overridden Methods
	@Override
	public void refresh()
	{
		this.getOverviewTablePanel().refresh(Methods.getDataForTable(Globals.ALOKASI), 
				Methods.createTableHeaderWithActionCell(Constants.PENGIRIMAN_TABLE_HEADERS));
	}
}
