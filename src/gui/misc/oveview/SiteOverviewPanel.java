package gui.misc.oveview;

import constants.Constants;
import constants.Globals;
import datadriver.Site;
import gui.misc.registration.SiteRegistration;
import methods.FileOperation;
import methods.Methods;

public class SiteOverviewPanel extends MiscOverviewPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2069254481746055193L;

	public SiteOverviewPanel()
	{
		super("Site", 
				FileOperation.loadSite(),
				Methods.createTableHeaderWithActionCell(Constants.SITE_TABLE_HEADERS),
				new SiteRegistration());
	}

	//Overridden Methods
	@Override
	public void saveData() 
	{
		Site site = (Site)this.getRegistrationForm().getData();
		
		FileOperation.exportData(site);
	}
	
	@Deprecated
	@Override
	public void resetDefaults(){}

	@Override
	public void refresh() 
	{
		this.refresh(FileOperation.loadSite(),
						Methods.createTableHeaderWithActionCell(Constants.SITE_TABLE_HEADERS));
	}
	
	@Override
	protected void registerMiscItem()
	{
		Methods.openMiscRegistrationForm(Globals.registerSite);
	}
}
