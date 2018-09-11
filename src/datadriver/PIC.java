package datadriver;

import org.w3c.dom.Document;

import constants.Constants;

public class PIC extends DataDriver
{
	//Constants
	public static final String NAME = "name";
	
	//Constructors
	public PIC(String key)
	{
		super(Constants.PIC_FOLDER_PATH, key, Constants.PIC_FILE_EXTENSION);
	}
	public PIC(Document doc)
	{
		super(doc, Constants.PIC_FOLDER_PATH, Constants.PIC_FILE_EXTENSION);
	}
	public PIC()
	{
		super(Constants.PIC_FOLDER_PATH, Constants.PIC_FILE_EXTENSION);
	}
	
	//Methods
	public String getName()
	{
		return this.getData(PIC.NAME);
	}
	public void setName(String name)
	{
		this.setData(PIC.NAME, name);
	}
	
	//Overridden Methods
	@Override
	public String[] getDataArray()
	{
		String[] str = new String[1];
		
		str[0] = this.getName();
		
		return str;
	}
	@Override
	public String getDisplayName() 
	{
		return this.getName();
	}

}
