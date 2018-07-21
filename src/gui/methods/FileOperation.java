package gui.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import constants.Constants;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.PIC;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import giantsweetroll.xml.dom.XMLManager;

public class FileOperation 
{
	public static void exportData(DataDriver data)
	{
		try 
		{
			File file = new File(data.getFileFullPath());
			
			if (!file.exists())
			{
				file.getParentFile().mkdirs();
			}
			
			Document doc = data.getDocument();
			List<Element> elements = XMLManager.getElements(doc.getDocumentElement().getChildNodes());
//			System.out.println(elements.size());
			
			XMLManager.exportXML(data.getDocument(), file, 3);
		} catch (TransformerException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static boolean dataExistsInDirectory(DataDriver data)
	{
		File file = new File(data.getFileFullPath());
		
		return file.exists();
	}
	
	public static List<Stock> loadStock()
	{
		return (List<Stock>)loadDataDriver(Constants.STOCK_FOLDER_PATH, Constants.STOCK_FILE_EXTENSION);
	}
	
	public static List<Alokasi> loadAlokasi()
	{
		return (List<Alokasi>)loadDataDriver(Constants.ALOKASI_FOLDER_PATH, Constants.ALOKASI_FILE_EXTENSION);
	}
	
	public static List<Site> loadSite()
	{
		return (List<Site>)loadDataDriver(Constants.SITE_FOLDER_PATH, Constants.SITE_FILE_EXTENSION);
	}
	
	public static List<PIC> loadPIC()
	{
		return (List<PIC>)loadDataDriver(Constants.PIC_FOLDER_PATH, Constants.PIC_FILE_EXTENSION);
	}
	
	public static List<Program> loadProgram()
	{
		return (List<Program>)loadDataDriver(Constants.PROGRAM_FOLDER_PATH, Constants.PROGRAM_FILE_EXTENSION);
	}
	
	public static List<? extends DataDriver> loadDataDriver(String folderPath, String extension)
	{
		List<DataDriver> list = new ArrayList<DataDriver>();
		
		try
		{
			File folder = new File(folderPath);
			File[] files = folder.listFiles();
			
			for (File file : files)
			{
				try 
				{
					Document doc = XMLManager.createDocument(file.getAbsolutePath(), false);
					if(extension.equals(Constants.STOCK_FILE_EXTENSION))
					{
						list.add(new Stock(doc));
					}
					else if (extension.equals(Constants.ALOKASI_FILE_EXTENSION))
					{
						list.add(new Alokasi(doc));
					}
				} 
				catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}
			}
		}
		catch(NullPointerException ex) {}
		
		return list;
	}
}
