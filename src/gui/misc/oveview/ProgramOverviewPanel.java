package gui.misc.oveview;

import constants.Constants;
import datadriver.Program;
import gui.misc.registration.ProgramRegistration;
import methods.FileOperation;
import methods.Methods;

public class ProgramOverviewPanel extends MiscOverviewPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -203850395590726559L;

	public ProgramOverviewPanel()
	{
		super("Program", 
				Methods.getDataForTable(FileOperation.loadProgram()),
				Methods.createTableHeaderWithActionCell(Constants.PROGRAM_TABLE_HEADERS),
				new ProgramRegistration());
	}
	
	//Overridden Methods
	@Override
	public void saveData() 
	{
		Program prog = (Program)this.getRegistrationForm().getData();
		
		FileOperation.exportData(prog);
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
		this.refresh(Methods.getDataForTable(FileOperation.loadProgram()),
						Methods.createTableHeaderWithActionCell(Constants.PROGRAM_TABLE_HEADERS));
	}

}
