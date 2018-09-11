package datadriver;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import constants.Constants;
import giantsweetroll.date.Date;

public class Alokasi extends DataDriver
{
	//Constants
	public static final String ITEM_NAME = "item_name",
								PROGRAM = "program",
								SITE = "site",
								AMOUNT = "amount",
								ITEM_INFO = "item_info",
								PIC = "pic",
								DATE_UPLOAD_DAY = "upload_day",
								DATE_UPLOAD_MONTH = "upload_month",
								DATE_UPLOAD_YEAR = "upload_year";
	
	//Constructor
	public Alokasi(String key)
	{
		super(Constants.ALOKASI_FOLDER_PATH, key, Constants.ALOKASI_FILE_EXTENSION);
	}
	public Alokasi(Document doc)
	{
		super(doc, Constants.ALOKASI_FOLDER_PATH, Constants.ALOKASI_FILE_EXTENSION);
	}
	
	//Methods
	public void setItem(String item)
	{
		this.setData(Alokasi.ITEM_NAME, item);
	}
	public String getItem()
	{
		return this.getData(Alokasi.ITEM_NAME);
	}
	public void setProgram(String program)
	{
		this.setData(Alokasi.PROGRAM, program);
	}
	public String getProgram()
	{
		return this.getData(Alokasi.PROGRAM);
	}
	public void setSite(String site)
	{
		this.setData(Alokasi.SITE, site);
	}
	public String getSite()
	{
		return this.getData(Alokasi.SITE);
	}
	public void setAmount(Long amount)
	{
		this.setAmount(Long.toString(amount));
	}
	public void setAmount(String amount)
	{
		this.setData(Alokasi.AMOUNT, amount);
	}
	public long getAmount()
	{
		return Long.parseLong(this.getData(Alokasi.AMOUNT));
	}
	public void setItemInfo(String info)
	{
		this.setData(Alokasi.ITEM_INFO, info);
	}
	public String getItemInfo()
	{
		return this.getData(Alokasi.ITEM_INFO);
	}
	public void setPIC(String pic)
	{
		this.setData(Alokasi.PIC, pic);
	}
	public String getPIC()
	{
		return this.getData(Alokasi.PIC);
	}
	public void setDateUpload(Date date)
	{
		this.setData(Alokasi.DATE_UPLOAD_DAY, Integer.toString(date.getDay()));
		this.setData(Alokasi.DATE_UPLOAD_MONTH, Integer.toString(date.getMonth()));
		this.setData(Alokasi.DATE_UPLOAD_YEAR, Integer.toString(date.getYear()));
	}
	public Date getUploadDate()
	{
		return new Date(Integer.parseInt(this.getData(Alokasi.DATE_UPLOAD_DAY)),
						Integer.parseInt(this.getData(Alokasi.DATE_UPLOAD_MONTH)),
						Integer.parseInt(this.getData(Alokasi.DATE_UPLOAD_YEAR)));
	}
	
	//Overridden Methods
	@Override
	public String[] getDataArray() 
	{
		List<String> list = new ArrayList<String>();
		
		list.add(this.getProgram());
		list.add(this.getSite());
		list.add(this.getItem());
		list.add(Long.toString(this.getAmount()));
		
		return list.toArray(new String[list.size()]);
	}
	@Override
	public String getDisplayName() 
	{
		return this.getItem() + "-" + this.getProgram() + "-" + this.getAmount();
	}
}
