package methods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import constants.Constants;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.PIC;
import datadriver.Pengiriman;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import giantsweetroll.files.FileManager;
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
			
			XMLManager.exportXML(data.getDocument(), file, 3);
		} catch (TransformerException e) 
		{
			e.printStackTrace();
		}
	}
	public static void exportData(List<? extends DataDriver> data)
	{
		for (int i=0; i<data.size(); i++)
		{
			FileOperation.exportData(data.get(i));
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
	
	public static List<Pengiriman> loadPengiriman()
	{
		return (List<Pengiriman>)loadDataDriver(Constants.PENGIRIMAN_FOLDER_PATH, Constants.PENGIRIMAN_FILE_EXTENSION);
	}
	
	public static List<? extends DataDriver> loadDataDriver(String folderPath, String extension)
	{
		List<DataDriver> list = new ArrayList<DataDriver>();
		List<File> files = new ArrayList<File>();
		
		FileManager.getListOfFiles(files, folderPath, true);
		
		for (File file : files)
		{
			if (file.isDirectory())
			{
				continue;
			}
			else
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
					else if (extension.equals(Constants.PIC_FILE_EXTENSION))
					{
						list.add(new PIC(doc));
					}
					else if (extension.equals(Constants.PROGRAM_FILE_EXTENSION))
					{
						list.add(new Program(doc));
					}
					else if (extension.equals(Constants.SITE_FILE_EXTENSION))
					{
						list.add(new Site(doc));
					}
					else if (extension.equals(Constants.PENGIRIMAN_FILE_EXTENSION))
					{
						list.add(new Pengiriman(doc));
					}
				}
				catch(Exception ex) 
				{
					ex.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public static void delete(DataDriver data)
	{
		File file = new File(data.getFileFullPath());
		file.delete();
	}
}
