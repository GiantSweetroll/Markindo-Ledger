package gui.pengiriman;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.Pengiriman;
import datadriver.Program;
import datadriver.Site;
import giantsweetroll.date.Date;
import giantsweetroll.message.MessageManager;
import gui.input.InputAmount;
import gui.input.InputDate;
import gui.input.InputDropDownMenu;
import gui.input.InputForm;
import gui.input.InputLongText;
import gui.input.InputText;
import methods.FileOperation;
import methods.Methods;

public class InputPengiriman extends InputForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3130923997446069483L;
	
	private InputDropDownMenu program, site, item, sender;
	private InputLongText siteDesc;
	private InputAmount sisaAlokasi, itemCount;
	private InputText penerima, infoFromSite, itemDesc;
	private InputDate datePengiriman;
	
	//Constructors
	public InputPengiriman()
	{
		super("Tambah Pengiriman", FramePanelConstants.PENGIRIMAN_OVERVIEW, FramePanelConstants.PENGIRIMAN_OVERVIEW);
	
		//Initialization
		this.program = new InputDropDownMenu("Nama Program", Globals.PROGRAMS.toArray(new Program[Globals.PROGRAMS.size()]));
		this.site = new InputDropDownMenu("Site", Globals.SITES.toArray(new Site[Globals.SITES.size()]))
				{
					/**
					 * 
					 */
					private static final long serialVersionUID = 4784576623662825800L;

					@Override
					public void actionPerformed(ActionEvent e)
					{
						displaySiteInfo();
					}
				};
		this.siteDesc = new InputLongText("Deskripsi Site");
		this.item = new InputDropDownMenu("Item", Globals.ALOKASI.toArray(new Alokasi[Globals.ALOKASI.size()]))
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 8297249340547522456L;
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						updateSisaAlokasi();
						displayItemInfo();
					}
				};
		this.sisaAlokasi = new InputAmount("Sisa Alokasi");
		this.itemCount = new InputAmount("Jumlah");
		this.itemDesc = new InputText("Keterangan Item");
		this.datePengiriman = new InputDate("Tanggal Kirim");
		this.sender = new InputDropDownMenu("Pengirim", Constants.SENDERS);
		this.penerima = new InputText("Penerima");
		this.infoFromSite = new InputText("Info Dari Site");
		
		//Properties
		this.itemCount.setData("0");
		this.updateSisaAlokasi();
		this.displayItemInfo();
		displaySiteInfo();
		this.siteDesc.setEnabled(false);
		this.sisaAlokasi.setEnabled(false);
		this.itemDesc.setEnabled(false);
		this.datePengiriman.getDateSelectionPanel().autoSetDate();
		
		//Add to form
		this.addFormElement(this.program);
		this.addFormElement(this.site);
		this.addFormElement(this.siteDesc);
		this.addFormElement(this.item);
		this.addFormElement(this.itemDesc);
		this.addFormElement(this.sisaAlokasi);
		this.addFormElement(this.itemCount);
		this.addFormElement(this.datePengiriman);
		this.addFormElement(this.sender);
		this.addFormElement(this.penerima);
		this.addFormElement(this.infoFromSite);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//Private methods
	private void updateSisaAlokasi()
	{
		sisaAlokasi.setData(Long.toString(Globals.ALOKASI.get(this.item.getSelectedIndex()).getAmount()));
	}
	private void displayItemInfo()
	{
		this.itemDesc.setData(Globals.ALOKASI.get(this.item.getSelectedIndex()).getItemInfo());
	}
	private void displaySiteInfo()
	{
		this.siteDesc.setData(Globals.SITES.get(this.site.getSelectedIndex()).getInfo());
	}
	
	//Public Methods
	public void updateAlokasiOptions()
	{
		this.item.setItems(Globals.ALOKASI.toArray(new Object[Globals.ALOKASI.size()]));
	}
	
	//Overridden Methods
	@Override
	public DataDriver getData() 
	{		
		Pengiriman send = new Pengiriman(this.program.getData().toString(),
											((Site)this.site.getData()).getID(),
											this.datePengiriman.getDate());
		
		send.setDateUpload(new Date());
		send.setItemName(this.item.getData().toString());
		send.setAmount(this.itemCount.getData());
		send.setSender(this.sender.getData().toString());
		send.setReceiver(this.penerima.getData());
		send.setInfoFromSite(this.infoFromSite.getData());
		
		if (!this.isNewEntry())
		{
			send.setID(((Pengiriman)this.oldData).getID());
		}
		
		return send;
	}

	@Override
	public void setData(DataDriver data) 
	{
		Pengiriman send = (Pengiriman)data;
		
		this.program.setData(Methods.findDataIndexByKey(Globals.PROGRAMS, send.getProgram()));
		this.item.setData(Methods.findDataIndexByKey(Globals.ALOKASI, send.getItemName()));
		this.site.setData(send.getSite());
		this.itemCount.setData(Long.toString(send.getAmount()));
		this.sender.setData(send.getSender());
		this.penerima.setData(send.getReceiver());
		this.infoFromSite.setData(send.getInfoFromSite());
		this.datePengiriman.setDate(send.getDateSent());
		
		this.oldData = send;
		this.setNewEntry(false);
	}

	@Override
	public boolean allFilled()
	{
		return this.itemCount.isFilled() && this.penerima.isFilled();
	}

	@Override
	public void savingDataStarting(){}

	@Override
	public void savingDataClosing()
	{
		Alokasi alokasi = Globals.ALOKASI.get(Methods.findDataIndexByDisplayName(Globals.ALOKASI, this.item.getData().toString()));
		if (this.isNewEntry())
		{
			alokasi.setAmount(alokasi.getAmount() - Long.parseLong(this.itemCount.getData()));
		}
		else
		{
			//Change back alokasi values
			long difference = ((Pengiriman)this.oldData).getAmount() - ((Pengiriman)this.getData()).getAmount();
			alokasi.setAmount(alokasi.getAmount() + difference);
		}
		FileOperation.exportData(alokasi);
		Globals.alokasiOverview.refresh();
		Globals.pengirimanOverview.refresh();
	}

	@Override
	public boolean canExport()
	{
		long amount = Long.parseLong(this.itemCount.getData());
		
		if (this.itemCount.getData().equals("") || this.itemCount.getData().equals("0"))
		{
			MessageManager.showErrorDialog("Input jumlah tidak sesuai", "Jumlah pengiriman tidak sesuai sisa alokasi");
			return false;
		}
		if(amount >= Globals.ALOKASI.get(this.item.getSelectedIndex()).getAmount() ||
				amount <=0)
		{
			MessageManager.showErrorDialog("Input jumlah tidak sesuai", "Jumlah pengiriman tidak sesuai sisa alokasi");
			return false;
		}
		
		return true;
	}
}
