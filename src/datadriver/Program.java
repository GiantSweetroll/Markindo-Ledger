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
	public Program(String name)
	{
		super(Constants.PROGRAM_FOLDER_PATH, name, Constants.PROGRAM_FILE_EXTENSION);
	}
	public Program(Document doc)
	{
		super(doc, Constants.PROGRAM_FOLDER_PATH, Constants.PROGRAM_FILE_EXTENSION);
	}
	public Program()
	{
		super(Constants.PROGRAM_FOLDER_PATH, Constants.PROGRAM_FILE_EXTENSION);
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
	@Override
	public String toString() 
	{
		return this.getName();
	}

}
