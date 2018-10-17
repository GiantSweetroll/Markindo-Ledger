package methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import constants.Constants;
import constants.Globals;
import constants.InsetsConstants;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.Pengiriman;
import datadriver.Site;
import datadriver.Stock;
import giantsweetroll.date.Date;
import giantsweetroll.xml.dom.XMLManager;
import gui.ActionPanel;
import gui.overviewpanel.OverviewPanel;
import gui.search.SearchFilterPanel;

public class Methods 
{
	public static List<Element> getElementsFromNode(Element element)
	{
		NodeList nodes = element.getChildNodes();
		
		return XMLManager.getElements(nodes);
	}
	
	public static String getDateAsString(Date date)
	{
		return date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
	}
	
	public static JComponent[][] getDataForTable(List<? extends DataDriver> list)
	{
		JComponent[][] obj = new JComponent[list.size()][];
		
		for (int i=0; i<list.size(); i++)
		{
			String[] arr = list.get(i).getDataArray();
			JComponent[] arr2 = new JComponent[arr.length + 1];
			for (int a=0; a<arr.length; a++)
			{
				arr2[a] = new JLabel(arr[a], SwingConstants.CENTER);
			}
			//Add ActionPanel to the last index of arr2
			arr2[arr2.length-1] = new ActionPanel(list.get(i));
			
			obj[i] = arr2;
		}
		
		return obj;
	}
	
	public static String[] createTableHeaderWithActionCell(String[] headers)
	{
		String[] arr = new String[headers.length + 1];
		
		for (int i=0; i<headers.length; i++)
		{
			arr[i] = headers[i];
		}
		
		arr[arr.length-1] = Constants.ACTION_CELL_NAME;
		
		return arr;
	}
	
	public static String[] getDisplayNames(List<? extends DataDriver> data)
	{
		String[] names = new String[data.size()];
		for (int i=0; i<data.size(); i++)
		{
			names[i] = data.get(i).toString();
		}
		
		return names;
	}
	
	public static void reloadGlobalVariables()
	{
		Globals.reloadPIC();
		Globals.reloadProgram();
		Globals.reloadSite();
		Globals.reloadStock();
		Globals.reloadAlokasi();
		Globals.reloadPengiriman();
	}
	
	public static int findDataIndexByDisplayName(List<? extends DataDriver> data, String displayName)
	{
		int a = -1;
		for (int i=0; i<data.size(); i++)
		{
			if (data.get(i).toString().equals(displayName))
			{
				a = i;
				break;
			}
		}
		
		return a;
	}
	public static int findDataIndexByKey(List<? extends DataDriver> data, String key)
	{
		int a = -1;
		for (int i=0; i<data.size(); i++)
		{
			if (data.get(i).getKey().equals(key))
			{
				a = i;
				break;
			}
		}
		
		return a;
	}
	
	public static void autoLayout(SpringLayout layout, JComponent c1, JComponent c2, JComponent parent)
	{
		layout.putConstraint(SpringLayout.WEST, c1, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, c1, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.SOUTH, c2);
		layout.putConstraint(SpringLayout.EAST, c1, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.EAST, parent);
	}
	
	public static String[] getListOfAreas()
	{
		Set<String> areaSet = new HashSet<String>();
		for (Site site : Globals.SITES)
		{
			areaSet.add(site.getArea());
		}
		List<String> sortedAreas = new ArrayList<String>(areaSet);
		Collections.sort(sortedAreas);
		
		return sortedAreas.toArray(new String[sortedAreas.size()]);
	}
	
	public static void filter(OverviewPanel overview)
	{
		overview.filter();
	}
	
	public static List<Stock> filterStock()
	{
		List<Stock> stocks = new ArrayList<Stock>();
		
		SearchFilterPanel filter = Globals.stockOverview.getSearchFilterPanel();
		String itemName;
		try
		{
			itemName = filter.getStockFilter().getItemName();
		}
		catch(NullPointerException ex)
		{
			return Globals.STOCKS;
		}
		for (Stock stock : Globals.STOCKS)
		{
			if (stock.getItemName().equals(itemName))
			{
				stocks.add(stock);
				break;
			}
		}
		
		return stocks;
	}
	
	public static List<Alokasi> filterAlokasi()
	{
		List<Alokasi> alokasi = new ArrayList<Alokasi>();
		List<Alokasi> temp = new ArrayList<Alokasi>();
		
		SearchFilterPanel filter = Globals.alokasiOverview.getSearchFilterPanel();
		String filterKey;
		//Filter by item
		try
		{
			filterKey = filter.getStockFilter().getItemName();
			for (Alokasi alok : Globals.ALOKASI)
			{
				if (alok.getItem().equals(filterKey))
				{
					alokasi.add(alok);
				}
			}
		}
		catch(NullPointerException ex) 
		{
			alokasi = Globals.ALOKASI;
		}
		
		//Filter by program
		try
		{
			filterKey = filter.getProgramFilter().getName();
			for (Alokasi alok : alokasi)
			{
				if (alok.getProgram().equals(filterKey))
				{
					temp.add(alok);
				}
			}
			alokasi = temp;
		}
		catch(NullPointerException ex) {}
		temp = new ArrayList<Alokasi>();
		
		//Filter by Area
		try
		{
			filterKey = filter.getAreaFilter();
			for (Alokasi alok : alokasi)
			{
				if (Methods.getSiteByID(alok.getSite()).getArea().equals(filterKey))
				{
					temp.add(alok);
				}
			}
			alokasi = temp;
		}
		catch(NullPointerException ex) {}
		temp = new ArrayList<Alokasi>();
		
		//Filter by Site
		try
		{
			filterKey = filter.getSiteFilter().getID();
			for (Alokasi alok : alokasi)
			{
				if (alok.getSite().equals(filterKey))
				{
					temp.add(alok);
				}
			}
			alokasi = temp;
		}
		catch(NullPointerException ex) {}
		temp = new ArrayList<Alokasi>();
		
		//Filter by PIC
		try
		{
			filterKey = filter.getPICFilter().getName();
			for (Alokasi alok : alokasi)
			{
				if (alok.getPIC().equals(filterKey))
				{
					temp.add(alok);
				}
			}
			alokasi = temp;
		}
		catch(NullPointerException ex) {}
		return alokasi;
	}
	
	public static List<Pengiriman> filterPengiriman()
	{
		List<Pengiriman> alokasi = new ArrayList<Pengiriman>();
		List<Pengiriman> temp = new ArrayList<Pengiriman>();
		
		SearchFilterPanel filter = Globals.pengirimanOverview.getSearchFilterPanel();
		String filterKey;
		//Filter by item
		try
		{
			filterKey = filter.getStockFilter().getItemName();
			for (Pengiriman alok : Globals.PENGIRIMAN)
			{
				if (alok.getItemName().equals(filterKey))
				{
					alokasi.add(alok);
				}
			}
		}
		catch(NullPointerException ex) 
		{
			alokasi = Globals.PENGIRIMAN;
		}
		
		//Filter by program
		try
		{
			filterKey = filter.getProgramFilter().getName();
			for (Pengiriman alok : alokasi)
			{
				if (alok.getProgram().equals(filterKey))
				{
					temp.add(alok);
				}
			}
			alokasi = temp;
		}
		catch(NullPointerException ex) {}
		temp = new ArrayList<Pengiriman>();
		
		//Filter by Area
		try
		{
			filterKey = filter.getAreaFilter();
			if(!filterKey.equals(""))
			{
				for (Pengiriman alok : alokasi)
				{
					if (Methods.getSiteByID(alok.getSite()).getArea().equals(filterKey))
					{
						temp.add(alok);
					}
				}
				alokasi = temp;
			}
		}
		catch(NullPointerException ex) {}
		temp = new ArrayList<Pengiriman>();
		
		//Filter by Site
		try
		{
			filterKey = filter.getSiteFilter().getID();
			for (Pengiriman alok : alokasi)
			{
				if (alok.getSite().equals(filterKey))
				{
					temp.add(alok);
				}
			}
			alokasi = temp;
		}
		catch(NullPointerException ex) {}
		temp = new ArrayList<Pengiriman>();
		
		//Filter by Sender
		try
		{
			filterKey = filter.getSenderFilter();
			if (!filterKey.equals(""))
			{
				for (Pengiriman alok : alokasi)
				{
					if (alok.getSender().equals(filterKey))
					{
						temp.add(alok);
					}
				}
				alokasi = temp;
			}
		}
		catch(NullPointerException ex) {}
		return alokasi;
	}
	
	public static Site getSiteByID(String id)
	{
		for (Site site : Globals.SITES)
		{
			if (site.getID().equals(id))
			{
				return site;
			}
		}
		
		return null;
	}
	
	public static String getSiteNameByID(String id)
	{
		Site site = Methods.getSiteByID(id);
		if (site==null)
		{
			return "";
		}
		else
		{
			return site.getName();
		}
	}
}
