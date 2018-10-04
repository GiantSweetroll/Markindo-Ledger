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
		this.program = new InputDropDownMenu("Nama Program", Methods.getDisplayNames(Globals.PROGRAMS));
		this.site = new InputDropDownMenu("Site", Methods.getDisplayNames(Globals.SITES));
		this.siteDesc = new InputLongText("Deskripsi Site");
		this.item = new InputDropDownMenu("Item", Methods.getDisplayNames(Globals.ALOKASI))
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 8297249340547522456L;
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						updateSisaAlokasi();
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
		this.updateSisaAlokasi();
		this.siteDesc.setEnabled(false);
		this.sisaAlokasi.setEnabled(false);
		this.itemDesc.setEnabled(false);
		
		//Add to form
		this.addFormElement(this.program);
		this.addFormElement(this.site);
		this.addFormElement(this.siteDesc);
		this.addFormElement(this.item);
		this.addFormElement(this.sisaAlokasi);
		this.addFormElement(this.itemCount);
		this.addFormElement(this.itemDesc);
		this.addFormElement(this.datePengiriman);
		this.addFormElement(this.sender);
		this.addFormElement(this.penerima);
		this.addFormElement(this.infoFromSite);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//private methods
	private void updateSisaAlokasi()
	{
		sisaAlokasi.setData(Long.toString(Globals.ALOKASI.get(this.item.getSelectedIndex()).getAmount()));
	}
	
	//Overridden Methods
	@Override
	public DataDriver getData() 
	{		
		Pengiriman send = new Pengiriman(this.program.getData(),
											this.site.getData(),
											this.datePengiriman.getDate());
		
		send.setDateUpload(new Date());
		send.setItemName(this.item.getData());
		send.setAmount(this.itemCount.getData());
		send.setSender(this.sender.getData());
		send.setReceiver(this.penerima.getData());
		send.setInfoFromSite(this.infoFromSite.getData());
		
		return send;
	}

	@Override
	public void setData(DataDriver data) 
	{
		Pengiriman send = (Pengiriman)data;
		
		this.program.setData(send.getProgram());
		this.item.setData(send.getItemName());
		this.site.setData(send.getSite());
		this.itemCount.setData(Long.toString(send.getAmount()));
		this.sender.setData(send.getSender());
		this.penerima.setData(send.getReceiver());
		this.infoFromSite.setData(send.getInfoFromSite());
		this.datePengiriman.setDate(send.getDateSent());
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
		Alokasi alokasi = Globals.ALOKASI.get(Methods.findDataIndexByDisplayName(Globals.ALOKASI, this.item.getData()));
		if (this.isNewEntry())
		{
			alokasi.setAmount(alokasi.getAmount() - Long.parseLong(this.itemCount.getData()));
		}
		FileOperation.exportData(alokasi);
		Globals.alokasiOverview.refresh();
		Globals.pengirimanOverview.refresh();
	}

	@Override
	public boolean canExport()
	{
		long amount = Long.parseLong(this.itemCount.getData());
		
		if(amount >= Globals.ALOKASI.get(this.item.getSelectedIndex()).getAmount() ||
				amount <=0)
		{
			MessageManager.showErrorDialog("Input jumlah tidak sesuai", "Jumlah pengiriman tidak sesuai sisa alokasi");
			return false;
		}
		
		return true;
	}
}
