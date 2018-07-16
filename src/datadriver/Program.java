package datadriver;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import constants.Constants;

public class Program extends DataDriver
{
	//Constants
	public static final String NAME = "name";
	
	//Constructors
	public Program(String key)
	{
		super(Constants.PROGRAM_FOLDER_PATH, key, Constants.PROGRAM_FILE_EXTENSION);
	}
	public Program(Document doc)
	{
		super(doc);
	}
	
	//Methods
	public void setName(String name)
	{
		this.setData(Program.NAME, name);
	}
	public String getName()
	{
		return this.getData(Program.NAME);
	}
	
	//Overridden Methods
	@Override
	public String[] getDataArray() 
	{
		List<String> list = new ArrayList<String>();
		
		list.add(this.getName());
		
		return list.toArray(new String[list.size()]);
	}

}