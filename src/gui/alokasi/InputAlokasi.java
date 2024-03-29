package gui.alokasi;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.Program;
import datadriver.Site;
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
	private InputAmount amount, stockLeft;
	private Date dateInput;
	
	public InputAlokasi()
	{
		super("Alokasi", FramePanelConstants.ALOKASI_OVERVIEW, FramePanelConstants.ALOKASI_OVERVIEW);
	
		this.program = new InputDropDownMenu("Nama Program", Globals.PROGRAMS.toArray(new Program[Globals.PROGRAMS.size()]));
		this.site = new InputDropDownMenu("Site", Globals.SITES.toArray(new Site[Globals.SITES.size()]))
				{
					/**
					 * 
					 */
					private static final long serialVersionUID = -3971103869344717638L;

					@Override
					public void actionPerformed(ActionEvent e)
					{
						displaySiteInfo();
					}
				};
		this.siteInfo = new InputLongText("Deskripsi Site");
		this.item = new InputDropDownMenu("Item", Globals.STOCKS.toArray(new Stock[Globals.STOCKS.size()]))
				{
					/**
					 * 
					 */
					private static final long serialVersionUID = -3971103869344717638L;

					@Override
					public void actionPerformed(ActionEvent e)
					{
						updateSisaStock();
					}
				};
		this.amount = new InputAmount("Jumlah");
		this.itemInfo = new InputText("Keterangan Item");
		this.pic = new InputDropDownMenu("PIC Shell", Methods.getDisplayNames(Globals.PICS));
		this.stockLeft = new InputAmount("Sisa Stok");
		
		//Properties
		this.siteInfo.setEnabled(false);
		this.stockLeft.setEnabled(false);
		this.amount.setData("0");
		this.updateSisaStock();
		this.displaySiteInfo();
		
		this.addFormElement(this.program);
		this.addFormElement(this.site);
		this.addFormElement(this.siteInfo);
		this.addFormElement(this.item);
		this.addFormElement(this.stockLeft);
		this.addFormElement(this.amount);
		this.addFormElement(this.itemInfo);
		this.addFormElement(this.pic);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//Private Methods
	private void updateSisaStock()
	{
		this.stockLeft.setData(Long.toString(Globals.STOCKS.get(this.item.getSelectedIndex()).getItemCount()));
	}
	private void displaySiteInfo()
	{
		this.siteInfo.setData(Globals.SITES.get(this.site.getSelectedIndex()).getInfo());
	}
	
	//public methods
	public void updateStockOptions()
	{
		this.item.setItems(Globals.STOCKS.toArray(new Object[Globals.STOCKS.size()]));
	}

	//Overridden Methods
	@Override
	public Alokasi getData()
	{
		Alokasi alo = new Alokasi(this.program.getData().toString(), 
									((Site)this.site.getData()).getID(), 
									this.pic.getData().toString(), 
									this.item.getData().toString());
		
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
		
		this.program.setData(Methods.findDataIndexByKey(Globals.PROGRAMS, alo.getProgram()));
		this.site.setData(Methods.findDataIndexByKey(Globals.SITES, alo.getSite()));
		this.item.setData(Methods.findDataIndexByKey(Globals.STOCKS, alo.getItem()));
		this.itemInfo.setData(alo.getItemInfo());
		this.amount.setData(Long.toString(alo.getAmount()));
		this.pic.setData(Methods.findDataIndexByKey(Globals.PICS, alo.getPIC()));
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
		Stock stock = Globals.STOCKS.get(Methods.findDataIndexByDisplayName(Globals.STOCKS, this.item.getData().toString()));
		if (this.isNewEntry())
		{
			stock.setItemCount(stock.getItemCount() - Long.parseLong(this.amount.getData()));
		}
		FileOperation.exportData(stock);
		Globals.stockOverview.refresh();
		Globals.alokasiOverview.refresh();
		Globals.pengirimanInput.updateAlokasiOptions();
	}
	
	@Override
	public boolean canExport()
	{
		if (this.amount.getData().equals("") || this.amount.getData().equals("0"))
		{
			MessageManager.showErrorDialog("Input jumlah tidak sesuai", "Jumlah alokasi tidak sesuai");
			return false;
		}
		else if (Long.parseLong(this.amount.getData()) > Globals.STOCKS.get(this.item.getSelectedIndex()).getItemCount() ||
				Long.parseLong(this.amount.getData()) <= 0)
		{
			MessageManager.showErrorDialog("Input jumlah tidak sesuai", "Jumlah alokasi tidak sesuai");
			return false;
		}
		
		return true;
	}
}
