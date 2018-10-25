package gui.misc.registration;

import datadriver.DataDriver;
import datadriver.PIC;
import gui.input.InputText;

public class PICRegistration extends MiscItemRegistration
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6716402630747228114L;

	private InputText inputName;
	
	//Constructors
	public PICRegistration()
	{
		//Initialization
		this.inputName = new InputText("Nama PIC");
		
		//Add form
		this.addForm(this.inputName);
	}
	
	//Overridden Methods
	@Override
	public void resetDefaults() 
	{
		this.inputName.resetDefaults();
	}

	@Override
	public void refresh() {
		this.inputName.refresh();
	}

	@Override
	public boolean allFilled() {
		return this.inputName.isFilled();
	}

	@Override
	public DataDriver getData() {
		PIC pic = new PIC(this.inputName.getData());
		
		pic.setName(this.inputName.getData());
		
		return pic;
	}
	
	@Override
	public void setData(DataDriver data) 
	{
		PIC pic = (PIC)data;
		this.inputName.setData(pic.getName());
		this.setAsNewEntry(false);
	}
}
