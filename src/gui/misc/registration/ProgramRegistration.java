package gui.misc.registration;

import datadriver.DataDriver;
import datadriver.Program;
import gui.input.InputText;

public class ProgramRegistration extends MiscItemRegistration
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1410312720639445527L;

	private InputText inputName;
	
	//Constructors
	public ProgramRegistration()
	{
		this.inputName = new InputText("Nama Program");
		
		this.addForm(this.inputName);
	}
	
	//Overridden Methods
	@Override
	public boolean allFilled()
	{
		return this.inputName.isFilled();
	}

	@Override
	public DataDriver getData() 
	{
		Program prog = new Program(this.inputName.getData());
		
		prog.setName(this.inputName.getData());
		
		return prog;
	}

	@Override
	public void resetDefaults()
	{
		this.inputName.resetDefaults();
	}

	@Override
	public void refresh() 
	{
		this.inputName.refresh();
	}
	
	@Override
	public void setData(DataDriver data)
	{
		Program prog = (Program)data;
		this.inputName.setData(prog.getName());
		this.setAsNewEntry(false);
	}
}
