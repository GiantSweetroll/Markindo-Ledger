package datadriver;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import giantsweetroll.xml.dom.XMLManager;

public abstract class DataDriver 
{
	private HashMap<String, String> dataMap;
	private String folderPath, key, extension;
	public static final String ROOT_NODE = "data";
	
	public DataDriver(String folderPath, String key, String extension)
	{
		this.folderPath = folderPath;
		this.key = key;
		this.dataMap = new HashMap<String, String>();
		this.extension = extension;
	}
	public DataDriver(Document doc)
	{
		this.dataMap = new HashMap<String, String>();
		this.setDocument(doc);
	}
	public DataDriver(String folderPath, String extension)
	{
		this.folderPath = folderPath;
		this.key = "";
		this.dataMap = new HashMap<String, String>();
		this.extension = extension;
	}
	
	//Methods
	public Document getDocument()
	{
		try 
		{
			Document doc = XMLManager.createDocument();
			Element root = doc.createElement(ROOT_NODE);
			
			for (Map.Entry<String, String> entry : this.dataMap.entrySet())
			{
				Element elm = doc.createElement(entry.getKey());
				elm.setTextContent(entry.getValue());
				root.appendChild(elm);
			}
			
			doc.appendChild(root);
			
			return doc;
		} 
		catch (ParserConfigurationException e) {e.printStackTrace();}
		
		return null;
	}
	public void setDocument(Document doc)
	{
		NodeList nodes = doc.getDocumentElement().getChildNodes();
		
		for (int i=0; i<nodes.getLength(); i++)
		{
			Node node = nodes.item(i);
			try
			{
				Element elm = (Element)node;
				this.setData(elm.getTagName(), elm.getTextContent());
			}
			catch(ClassCastException ex) {}
		}
	}
	public void setData(String key, String data)
	{
		this.dataMap.put(key, data);
	}
	public String getData(String key)
	{
		return this.dataMap.get(key);
	}
	public void setFolderPath(String folderPath)
	{
		this.folderPath = folderPath;
	}
	public String getFolderPath()
	{
		return this.folderPath;
	}
	public String getKey()		//Gets the key/id of the Data (basically file name without extension)
	{
		return this.key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public void setExtension(String extension)
	{
		this.extension = extension;
	}
	public String getExtension()
	{
		return this.extension;
	}
	public String getFileName()		//get the file name if the data were to be saved into file
	{
		return this.key + this.extension;
	}
	public String getFileFullPath()
	{
		return this.folderPath + this.getFileName();
	}
	
	//Abstract Methods
	public abstract String[] getDataArray();
	public abstract String getDisplayName();
}
