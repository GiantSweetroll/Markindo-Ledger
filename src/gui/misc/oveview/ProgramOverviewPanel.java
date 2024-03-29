package gui.misc.oveview;

import constants.Constants;
import constants.Globals;
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
				FileOperation.loadProgram(),
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
	public void resetDefaults(){}

	@Override
	public void refresh() 
	{
		this.refresh(FileOperation.loadProgram(),
						Methods.createTableHeaderWithActionCell(Constants.PROGRAM_TABLE_HEADERS));
	}

	@Override
	protected void registerMiscItem()
	{
		Methods.openMiscRegistrationForm(Globals.registerProgram);
	}
}
