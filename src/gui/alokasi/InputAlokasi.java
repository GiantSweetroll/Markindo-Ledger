package gui.alokasi;

import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import giantsweetroll.date.Date;
import gui.input.InputAmount;
import gui.input.InputDropDownMenu;
import gui.input.InputForm;
import gui.input.InputLongText;
import gui.input.InputText;
import gui.methods.Methods;

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
	
		this.program = new InputDropDownMenu("Nama Program", Methods.getDisplayNames(Globals.PROGRAMS));
		this.site = new InputDropDownMenu("Site", Methods.getDisplayNames(Globals.SITES));
		this.siteInfo = new InputLongText("Deskripsi Site");
		this.item = new InputDropDownMenu("Item", Methods.getDisplayNames(Globals.STOCKS));
		this.amount = new InputAmount("Jumlah");
		this.itemInfo = new InputText("Keterangan Item");
		this.pic = new InputDropDownMenu("PIC Shell", Methods.getDisplayNames(Globals.PICS));
		
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
		Alokasi alo = new Alokasi(this.program.getData() + this.item.getData());
		
		alo.setProgram(this.program.getData());
		alo.setSite(this.site.getData());
		alo.setItem(this.item.getData());
		alo.setAmount(this.amount.getData());
		alo.setItemInfo(this.itemInfo.getData());
		alo.setPIC(this.pic.getData());
		try
		{
			alo.setDateUpload(this.dateInput);
		}
		catch(NullPointerException ex)
		{
			alo.setDateUpload(new Date());
		}
		this.dateInput = null;
		
		return alo;
	}

	@Override
	public void setData(DataDriver data)
	{
		Alokasi alo = (Alokasi)data;
		
		this.program.setData(alo.getProgram());
		this.site.setData(alo.getSite());
		this.item.setData(alo.getItem());
		this.amount.setData(alo.getItemInfo());
		this.pic.setData(alo.getPIC());
		this.dateInput = alo.getUploadDate();
	}

	@Override
	public boolean allFilled() 
	{
		return this.program.isFilled() && 
				this.site.isFilled() && 
				this.item.isFilled() && 
				this.amount.isFilled() &&
				this.pic.isFilled();
	}

}
