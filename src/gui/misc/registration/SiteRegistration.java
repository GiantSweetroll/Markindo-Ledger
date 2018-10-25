package gui.misc.registration;

import datadriver.DataDriver;
import datadriver.Site;
import gui.input.InputLongText;
import gui.input.InputText;

public class SiteRegistration extends MiscItemRegistration
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800473082978633184L;

	private InputText inputName, inputArea, inputCode;
	private InputLongText inputInfo;
	
	//Constructors
	public SiteRegistration()
	{
		//Initialization
		this.inputName = new InputText("Nama Site");
		this.inputArea = new InputText("Area");
		this.inputCode = new InputText("Kode Site");
		this.inputInfo = new InputLongText("Keterangan Site");
		
		//Add form
		this.addForm(this.inputName);
		this.addForm(this.inputArea);
		this.addForm(this.inputCode);
		this.addForm(this.inputInfo);
	}
	
	//Overridden Methods
	@Override
	public boolean allFilled() 
	{
		return this.inputName.isFilled() &&
				this.inputArea.isFilled() &&
				this.inputCode.isFilled();
	}

	@Override
	public Site getData()
	{
		Site site = new Site(this.inputCode.getData());
		
		site.setArea(this.inputArea.getData());
		site.setName(this.inputName.getData());
		site.setInfo(this.inputInfo.getData());
		
		return site;
	}

	@Override
	public void resetDefaults()
	{
		this.inputArea.resetDefaults();
		this.inputCode.resetDefaults();
		this.inputInfo.resetDefaults();
		this.inputName.resetDefaults();
	}

	@Override
	public void refresh() {
		this.inputArea.refresh();
		this.inputCode.refresh();
		this.inputInfo.refresh();
		this.inputName.refresh();
	}
	
	@Override
	public void setData(DataDriver data)
	{
		Site site = (Site)data;
		this.oldData = site;
		this.inputName.setData(site.getName());
		this.inputArea.setData(site.getArea());
		this.inputCode.setData(site.getID());
		this.inputInfo.setData(site.getInfo());
		
		this.setAsNewEntry(false);
	}
}
