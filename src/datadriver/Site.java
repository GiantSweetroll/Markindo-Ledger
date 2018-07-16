package datadriver;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import constants.Constants;

public class Site extends DataDriver
{
	//Constants
	public static final String NAME = "name",
								INFO = "info",
								CODE = "code";
	
	//Constructors
	public Site(String key)
	{
		super(Constants.SITE_FOLDER_PATH, key, Constants.SITE_FILE_EXTENSION);
	}
	public Site(Document doc)
	{
		super(doc);
	}
	
	//Methods
	public void setName(String name)
	{
		this.setData(Site.NAME, name);
	}
	public String getName()
	{
		return this.getData(Site.NAME);
	}
	public void setInfo(String info)
	{
		this.setData(Site.INFO, info);
	}
	public String getInfo()
	{
		return this.getData(Site.INFO);
	}
	public void setCode(String code)
	{
		this.setData(Site.CODE, code);
	}
	public String getCode()
	{
		return this.getData(Site.CODE);
	}
	
	//Overridden Methods
	@Override
	public String[] getDataArray()
	{
		List<String> list = new ArrayList<String>();
		
		list.add(this.getName());
		list.add(this.getInfo());
		list.add(this.getCode());
		
		return list.toArray(new String[list.size()]);
	}
}
