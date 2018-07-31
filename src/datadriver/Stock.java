package datadriver;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import constants.Constants;
import giantsweetroll.date.Date;
import gui.methods.Methods;

public class Stock extends DataDriver
{
	//Constants
	public static final String ITEM_NAME = "item_name",
								ITEM_COUNT = "item_count",
								ITEM_DETAILS = "item_details",
								DATE_LAST_MODIFIED_DAY = "last_modified_day",
								DATE_LAST_MODIFIED_MONTH = "last_modified_month",
								DATE_LAST_MODIFIED_YEAR = "last_modified_year",
								DATE_FIRST_ENTERED_DAY = "first_entered_day",
								DATE_FIRST_ENTERED_MONTH = "first_entered_month",
								DATE_FIRST_ENTERED_YEAR = "first_entered_year";
	
	
	public Stock(String key)
	{
		super(Constants.STOCK_FOLDER_PATH, key, Constants.STOCK_FILE_EXTENSION);
	}
	public Stock(Document stockDoc)
	{
		super(stockDoc);
	}
	
	//Methods
	public void setItemName(String name)
	{
		this.setData(Stock.ITEM_NAME, name);
	}
	public String getItemName()
	{
		return this.getData(Stock.ITEM_NAME);
	}
	public void setItemCount(long count)
	{
		this.setData(Stock.ITEM_COUNT, Long.toString(count));
	}
	public void setItemCount(String count)
	{
		this.setItemCount(Long.parseLong(count));
	}
	public long getItemCount()
	{
		return Long.parseLong(this.getData(Stock.ITEM_COUNT));
	}
	public void setItemDescription(String description)
	{
		this.setData(Stock.ITEM_DETAILS, description);
	}
	public String getItemDescription()
	{
		return this.getData(Stock.ITEM_DETAILS);
	}
	public void setDateLastModified(Date date)
	{
		this.setData(Stock.DATE_LAST_MODIFIED_DAY, Integer.toString(date.getDay()));
		this.setData(Stock.DATE_LAST_MODIFIED_MONTH, Integer.toString(date.getMonth()));
		this.setData(Stock.DATE_LAST_MODIFIED_YEAR, Integer.toString(date.getYear()));
	}
	public Date getDateLastModified()
	{
		return new Date(Integer.parseInt(this.getData(Stock.DATE_LAST_MODIFIED_DAY)),
						Integer.parseInt(this.getData(Stock.DATE_LAST_MODIFIED_MONTH)),
						Integer.parseInt(this.getData(Stock.DATE_LAST_MODIFIED_YEAR)));
	}
	public void setDateFirstEntered(Date date)
	{
		this.setData(Stock.DATE_FIRST_ENTERED_DAY, Integer.toString(date.getDay()));
		this.setData(Stock.DATE_FIRST_ENTERED_MONTH, Integer.toString(date.getMonth()));
		this.setData(Stock.DATE_FIRST_ENTERED_YEAR, Integer.toString(date.getYear()));
	}
	public Date getDateFirstEntered()
	{
		return new Date(Integer.parseInt(this.getData(Stock.DATE_FIRST_ENTERED_DAY)),
						Integer.parseInt(this.getData(Stock.DATE_FIRST_ENTERED_MONTH)),
						Integer.parseInt(this.getData(Stock.DATE_FIRST_ENTERED_YEAR)));
	}
	
	//Overridden Methods
	@Override
	public String[] getDataArray() 
	{
		List<String> list = new ArrayList<String>();
		
		list.add(this.getItemName());
		list.add(Long.toString(this.getItemCount()));
//		list.add(this.getItemDescription());
		list.add(Methods.getDateAsString(this.getDateFirstEntered()));
		list.add(Methods.getDateAsString(this.getDateLastModified()));
		
		return list.toArray(new String[list.size()]);
	}
	@Override
	public String getDisplayName() 
	{
		return this.getItemName();
	}
}
