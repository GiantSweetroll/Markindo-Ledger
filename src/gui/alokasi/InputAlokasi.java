package gui.alokasi;

import java.awt.Color;

import javax.swing.BorderFactory;

import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.Stock;
import giantsweetroll.date.Date;
import giantsweetroll.message.MessageManager;
import gui.input.InputAmount;
import gui.input.InputDropDownMenu;
import gui.input.InputForm;
import gui.input.InputLongText;
import gui.input.InputText;
import methods.FileOperation;
import methods.Methods;

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
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	//Overridden Methods
	@Override
	public Alokasi getData()
	{
		Alokasi alo = new Alokasi(this.program.getData(), this.site.getData(), this.pic.getData(), this.item.getData());
		
		alo.setAmount(this.amount.getData());
		alo.setItemInfo(this.itemInfo.getData());
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
		
		this.setNewEntry(false);
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

	@Override
	public void savingDataStarting() {}

	@Override
	public void savingDataClosing() 
	{
		Stock stock = Globals.STOCKS.get(Methods.findDataIndexByDisplayName(Globals.STOCKS, this.item.getData()));
		if (this.isNewEntry())
		{
			stock.setItemCount(stock.getItemCount() - Long.parseLong(this.amount.getData()));
		}
		FileOperation.exportData(stock);
		Globals.stockOverview.refresh();
		Globals.alokasiOverview.refresh();
	}
	
	@Override
	public boolean canExport()
	{
		if (Long.parseLong(this.amount.getData()) > Globals.STOCKS.get(Methods.findDataIndexByDisplayName(Globals.STOCKS, this.item.getData())).getItemCount() ||
				Long.parseLong(this.amount.getData()) <= 0)
		{
			MessageManager.showErrorDialog("Input jumlah tidak sesuai", "Jumlah alokasi tidak sesuai");
			return false;
		}
		
		return true;
	}
}
