package datadriver;

import java.util.ArrayList;

import org.w3c.dom.Document;

import constants.Constants;
import giantsweetroll.date.Date;
import methods.IDGenerator;
import methods.Methods;

public class Pengiriman extends DataDriver
{
	//Constants
	public static final String ITEM_NAME = "item_name",
								PROGRAM = "program",
								SITE = "site",
								AMOUNT = "amount",
								DATE_UPLOAD_DAY = "upload_day",
								DATE_UPLOAD_MONTH = "upload_month",
								DATE_UPLOAD_YEAR = "upload_year",
								SENDER = "sender",
								RECEIVER = "receiver",
								INFO_FROM_SITE = "info_from_site",
								DATE_SENT_DAY = "sent_day",
								DATE_SENT_MONTH = "sent_month",
								DATE_SENT_YEAR = "sent_year",
								ID = "id";
	
	//Constructor
	public Pengiriman(String program, String siteID, Date dateSent)
	{
		super(Constants.PENGIRIMAN_FOLDER_PATH + program + "/" + 
													siteID + "/" + 
													dateSent.getYear() + "/" + 
													dateSent.getMonth() + "/" +
													dateSent.getDay() + "/", Constants.PENGIRIMAN_FILE_EXTENSION);
		this.setID(IDGenerator.generate(IDGenerator.PENGIRIMAN));
		this.setProgram(program);
		this.setSite(siteID);
		this.setDateSent(dateSent);
	}
	public Pengiriman(Document doc)
	{
		super(doc, Constants.PENGIRIMAN_FOLDER_PATH, Constants.PENGIRIMAN_FILE_EXTENSION);
		
		this.setKey(this.getID());
		Date dateSent = this.getDateSent();
		this.setFolderPath(Constants.PENGIRIMAN_FOLDER_PATH + "/" +
							this.getProgram() + "/" +
							this.getSite() + "/" +
							dateSent.getDay() + "/" +
							dateSent.getMonth() + "/" +
							dateSent.getYear() + "/");
	}
	
	//Methods
	public void setItemName(String itemName)
	{
		this.setData(Pengiriman.ITEM_NAME, itemName);
	}
	public String getItemName()
	{
		return this.getData(Pengiriman.ITEM_NAME);
	}
	public void setProgram(String program)
	{
		this.setData(Pengiriman.PROGRAM, program);
	}
	public String getProgram()
	{
		return this.getData(Pengiriman.PROGRAM);
	}
	public void setSite(String site)
	{
		this.setData(Pengiriman.SITE, site);
	}
	public String getSite()
	{
		return this.getData(Pengiriman.SITE);
	}
	public void setAmount(long amount)
	{
		this.setAmount(Long.toString(amount));
	}
	public void setAmount(String amount)
	{
		this.setData(Pengiriman.AMOUNT, amount);
	}
	public long getAmount()
	{
		return Long.parseLong(this.getData(Pengiriman.AMOUNT));
	}
	public void setDateUpload(Date date)
	{
		this.setData(Pengiriman.DATE_UPLOAD_DAY, Integer.toString(date.getDay()));
		this.setData(Pengiriman.DATE_UPLOAD_MONTH, Integer.toString(date.getMonth()));
		this.setData(Pengiriman.DATE_UPLOAD_YEAR, Integer.toString(date.getYear()));
	}
	public Date getDateUpload()
	{
		return new Date(Integer.parseInt(this.getData(Pengiriman.DATE_UPLOAD_DAY)),
						Integer.parseInt(this.getData(Pengiriman.DATE_UPLOAD_MONTH)),
						Integer.parseInt(this.getData(Pengiriman.DATE_UPLOAD_YEAR)));
	}
	public void setSender(String sender)
	{
		this.setData(Pengiriman.SENDER, sender);
	}
	public String getSender()
	{
		return this.getData(Pengiriman.SENDER);
	}
	public void setReceiver(String receiver)
	{
		this.setData(Pengiriman.RECEIVER, receiver);
	}
	public String getReceiver()
	{
		return this.getData(Pengiriman.RECEIVER);
	}
	public void setInfoFromSite(String info)
	{
		this.setData(Pengiriman.INFO_FROM_SITE, info);
	}
	public String getInfoFromSite()
	{
		return this.getData(Pengiriman.INFO_FROM_SITE);
	}
	public void setDateSent(Date date)
	{
		this.setData(Pengiriman.DATE_UPLOAD_DAY, Integer.toString(date.getDay()));
		this.setData(Pengiriman.DATE_UPLOAD_MONTH, Integer.toString(date.getMonth()));
		this.setData(Pengiriman.DATE_UPLOAD_YEAR, Integer.toString(date.getYear()));
	}
	public Date getDateSent()
	{
		return new Date(Integer.parseInt(this.getData(Pengiriman.DATE_UPLOAD_DAY)),
						Integer.parseInt(this.getData(Pengiriman.DATE_UPLOAD_MONTH)),
						Integer.parseInt(this.getData(Pengiriman.DATE_UPLOAD_YEAR)));
	}
	public void setID(String id)
	{
		super.setKey(id);
		this.setData(Pengiriman.ID, id);
	}
	public String getID()
	{
		return this.getData(Pengiriman.ID);
	}

	//Overridden Methods
	@Override
	public String[] getDataArray()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		list.add(Methods.getDateAsString(this.getDateSent()));
		list.add(this.getProgram());
		list.add(Methods.getSiteNameByID((this.getSite())));
		list.add(this.getItemName());
		list.add(Long.toString(this.getAmount()));
		list.add(this.getSender());
		
		return list.toArray(new String[list.size()]);
	}

	@Override
	public String toString() 
	{
		return this.getItemName();
	}
	
	@Override
	public String getKey()
	{
		return this.getID();
	}
}
