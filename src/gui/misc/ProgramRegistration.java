package gui.misc;

import datadriver.DataDriver;
import input.InputText;

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
	public DataDriver getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetDefaults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
