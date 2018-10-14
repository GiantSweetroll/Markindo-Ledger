package gui.alokasi;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import gui.overviewpanel.OverviewPanel;
import gui.search.SearchFilterItem;
import methods.Methods;

public class AlokasiOverview extends OverviewPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5951592076700422987L;

	public AlokasiOverview()
	{
		super("Alokasi", 
				FramePanelConstants.ALOKASI_INPUT,
				Methods.getDataForTable(Globals.ALOKASI), 
				Methods.createTableHeaderWithActionCell(Constants.ALOKASI_TABLE_HEADERS));
		
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Program", SearchFilterItem.PROGRAM, Methods.getDisplayNames(Globals.PROGRAMS)));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Area", SearchFilterItem.AREA, Methods.getListOfAreas()));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("Site", SearchFilterItem.SITE, Methods.getDisplayNames(Globals.SITES)));
		this.getSearchFilterPanel().addFilter(new SearchFilterItem("PIC", SearchFilterItem.PIC, Methods.getDisplayNames(Globals.PICS)));
	}
	
	//Overridden Methods
	@Override
	public void refresh()
	{
		this.getOverviewTablePanel().refresh(Methods.getDataForTable(Globals.ALOKASI), 
												Methods.createTableHeaderWithActionCell(Constants.ALOKASI_TABLE_HEADERS));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.PROGRAM, Globals.PROGRAMS.toArray(new Program[Globals.PROGRAMS.size()]));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.SITE, Globals.SITES.toArray(new Site[Globals.SITES.size()]));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.STOCK, Globals.STOCKS.toArray(new Stock[Globals.STOCKS.size()]));
		this.getSearchFilterPanel().updateItem(SearchFilterItem.AREA, Methods.getListOfAreas());
	}

	@Override
	public void filter() {
		// TODO Auto-generated method stub
		
	}
}
