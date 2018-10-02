package gui.pengiriman;

import java.awt.Color;

import javax.swing.BorderFactory;

import constants.Constants;
import constants.FramePanelConstants;
import constants.Globals;
import datadriver.DataDriver;
import datadriver.Pengiriman;
import giantsweetroll.date.Date;
import gui.input.InputAmount;
import gui.input.InputDate;
import gui.input.InputDropDownMenu;
import gui.input.InputForm;
import gui.input.InputLongText;
import gui.input.InputText;
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
		this.item = new InputDropDownMenu("Item", Methods.getDisplayNames(Globals.ALOKASI));
		this.sisaAlokasi = new InputAmount("Sisa Alokasi");
		this.itemCount = new InputAmount("Jumlah");
		this.itemDesc = new InputText("Keterangan Item");
		this.datePengiriman = new InputDate("Tanggal Kirim");
		this.sender = new InputDropDownMenu("Pengirim", Constants.SENDERS);
		this.penerima = new InputText("Penerima");
		this.infoFromSite = new InputText("Info Dari Site");
		
		this.addFormElement(this.program);
		this.addFormElement(this.site);
		this.addFormElement(this.siteDesc);
		this.addFormElement(this.sisaAlokasi);
		this.addFormElement(this.itemCount);
		this.addFormElement(this.itemDesc);
		this.addFormElement(this.datePengiriman);
		this.addFormElement(this.sender);
		this.addFormElement(this.penerima);
		this.addFormElement(this.infoFromSite);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//Overridden Methods
	@Override
	public DataDriver getData() 
	{
		String key = Methods.getDateAsString(this.datePengiriman.getDate()) +
						this.program.getData() +
						this.site.getData() +
						this.item.getData() +
						this.sender.getData();
		
		Pengiriman send = new Pengiriman(key);
		
		send.setDateUpload(new Date());
		send.setProgram(this.program.getData());
		send.setItemName(this.item.getData());
		send.setSite(this.site.getData());
		send.setAmount(this.itemCount.getData());
		send.setSender(this.sender.getData());
		send.setReceiver(this.penerima.getData());
		send.setInfoFromSite(this.infoFromSite.getData());
		send.setDateSent(this.datePengiriman.getDate());
		
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void savingDataStarting()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savingDataClosing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExport() 
	{
		// TODO Auto-generated method stub
		return false;
	}
}
