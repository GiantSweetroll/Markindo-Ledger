package stok;

import constants.FramePanelConstants;
import datadriver.DataDriver;
import datadriver.Stock;
import giantsweetroll.date.Date;
import input.InputAmount;
import input.InputForm;
import input.InputText;

public class InputStock extends InputForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2425978067883614433L;

	private InputText itemName, itemDesc;
	private InputAmount itemCount;
	private Date dateEnter, dateMod;
	
	//Constructor
	public InputStock()
	{
		super("Input Stok", FramePanelConstants.STOCK_OVERVIEW, FramePanelConstants.STOCK_OVERVIEW);
		
		this.itemName = new InputText("Nama Item");
		this.itemDesc = new InputText("Keterangan Item");
		this.itemCount = new InputAmount("Jumlah Item");
		
		this.addFormElement(itemName);
		this.addFormElement(itemCount);
		this.addFormElement(itemDesc);
	}

	//Overridden Methods
	@Override
	public Stock getData() 
	{
		Stock stock = new Stock(this.itemName.getData());
		
		stock.setItemName(this.itemName.getData());
		stock.setItemCount(this.itemCount.getData());
		stock.setItemDescription(this.itemDesc.getData());
		
		if(this.dateEnter == null)
		{
			stock.setDateFirstEntered(new Date());
		}
		else
		{
			stock.setDateFirstEntered(this.dateEnter);
		}
		
		this.dateMod = new Date();
		stock.setDateLastModified(this.dateMod);
		
		return stock;
	}

	@Override
	public void setData(DataDriver data)
	{
		if (data instanceof Stock)
		{
			this.setNewEntry(false);
			Stock stock = (Stock)data;
			
			this.itemName.setData(stock.getItemName());
			this.itemCount.setData(Long.toString(stock.getItemCount()));
			this.itemDesc.setData(stock.getItemDescription());
			this.dateEnter = stock.getDateFirstEntered();
			this.dateMod = stock.getDateLastModified();
		}
	}

	@Override
	public void resetDefaults()
	{
		super.resetDefaults();
		
		this.dateEnter = null;
		this.dateMod = null;
	}
}