package datadriver;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import constants.Constants;
import methods.IDGenerator;

public class Site extends DataDriver
{
	//Constants
	public static final String 	ID = "id",
								NAME = "name",
								INFO = "info",
								AREA = "area";
	
	//Constructors
	public Site(String area)
	{
		super(Constants.SITE_FOLDER_PATH, Constants.SITE_FILE_EXTENSION);
	
		this.setArea(area);
		this.setID(IDGenerator.generate(IDGenerator.SITE));
		this.setFolderPath(Constants.SITE_FOLDER_PATH + this.getArea() + "/");
	}
	public Site(Document doc)
	{
		super(doc, Constants.SITE_FOLDER_PATH, Constants.SITE_FILE_EXTENSION);
		this.setFolderPath(Constants.SITE_FOLDER_PATH + this.getArea() + "/");
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
	public void setArea(String area)
	{
		this.setData(Site.AREA, area);
	}
	public String getArea()
	{
		return this.getData(Site.AREA);
	}
	public void setID(String id)
	{
		this.setData(Site.ID, id);
	}
	public String getID()
	{
		return this.getData(Site.ID);
	}
	
	//Overridden Methods
	@Override
	public String[] getDataArray()
	{
		List<String> list = new ArrayList<String>();
		
		list.add(this.getName());
		list.add(this.getArea());
		list.add(this.getInfo());
		
		return list.toArray(new String[list.size()]);
	}
	@Override
	public String toString() 
	{
		return this.getName();
	}
	@Override
	public String getKey()
	{
		return this.getID();
	}
}
