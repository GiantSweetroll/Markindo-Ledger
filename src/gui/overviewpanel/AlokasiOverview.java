package gui.overviewpanel;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
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
	}
	
	
	//Overridden Methods
	@Override
	public void refresh()
	{
		this.getOverviewTablePanel().refresh(Methods.getDataForTable(Globals.ALOKASI), 
				Methods.createTableHeaderWithActionCell(Constants.ALOKASI_TABLE_HEADERS));
	}
}
