package gui.misc.oveview;

import constants.Constants;
import datadriver.PIC;
import gui.misc.registration.PICRegistration;
import methods.FileOperation;
import methods.Methods;

public class PICOverviewPanel extends MiscOverviewPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8160251600073604925L;

	public PICOverviewPanel()
	{
		super("PIC", 
				Methods.getDataForTable(FileOperation.loadPIC()),
				Methods.createTableHeaderWithActionCell(Constants.PIC_TABLE_HEADERS),
				new PICRegistration());
	}
	
	@Override
	public void saveData()
	{
		PIC pic = (PIC)this.getRegistrationForm().getData();
		
		FileOperation.exportData(pic);
	}
	
	@Deprecated
	@Override
	public void resetDefaults()
	{
		this.getOveviewTablePanel().resetDefaults();
	}

	@Override
	public void refresh() 
	{
		this.refresh(Methods.getDataForTable(FileOperation.loadPIC()),
						Methods.createTableHeaderWithActionCell(Constants.PIC_TABLE_HEADERS));
	}

}
