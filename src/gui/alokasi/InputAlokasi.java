package gui.alokasi;

import constants.FramePanelConstants;
import datadriver.Alokasi;
import datadriver.DataDriver;
import giantsweetroll.date.Date;
import input.InputAmount;
import input.InputDropDownMenu;
import input.InputForm;
import input.InputLongText;
import input.InputText;

public class InputAlokasi extends InputForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2001291239027629220L;

	private InputDropDownMenu program, site, item, pic;
	private InputLongText siteInfo;
	private InputText itemInfo;
	private InputAmount amount;
	private Date dateInput;
	
	public InputAlokasi()
	{
		super("Alokasi", FramePanelConstants.ALOKASI_OVERVIEW, FramePanelConstants.ALOKASI_OVERVIEW);
	
		this.program = new InputDropDownMenu("Nama Program", new String[1]);
		this.site = new InputDropDownMenu("Site", new String[1]);
		this.siteInfo = new InputLongText("Deskripsi Site");
		this.item = new InputDropDownMenu("Item", new String[1]);
		this.amount = new InputAmount("Jumlah");
		this.itemInfo = new InputText("Keterangan Item");
		this.pic = new InputDropDownMenu("PIC Shell", new String[1]);
		
		this.addFormElement(this.program);
		this.addFormElement(this.site);
		this.addFormElement(this.siteInfo);
		this.addFormElement(this.item);
		this.addFormElement(this.amount);
		this.addFormElement(this.itemInfo);
		this.addFormElement(this.pic);
	}

	//Overridden Methods
	@Override
	public Alokasi getData()
	{
		return null;
	}

	@Override
	public void setData(DataDriver data) {
		// TODO Auto-generated method stub
		
	}

}
