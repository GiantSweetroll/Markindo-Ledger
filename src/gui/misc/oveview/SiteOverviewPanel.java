package gui.misc.oveview;

import constants.Constants;
import datadriver.Site;
import gui.methods.FileOperation;
import gui.methods.Methods;
import gui.misc.registration.SiteRegistration;
import gui.table.OverviewTable;

public class SiteOverviewPanel extends MiscOverviewPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2069254481746055193L;

	public SiteOverviewPanel()
	{
		super("Site", 
				new OverviewTable(
						Methods.getDataForTable(FileOperation.loadSite()),
						Methods.createTableHeaderWithActionCell(Constants.SITE_TABLE_HEADERS)),
				new SiteRegistration());
	}

	//Overridden Methods
	@Override
	public void saveData() 
	{
		Site site = (Site)this.getRegistrationForm().getData();
		
		FileOperation.exportData(site);
	}
}
