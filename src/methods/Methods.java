package methods;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import constants.Constants;
import constants.Globals;
import constants.InsetsConstants;
import datadriver.DataDriver;
import giantsweetroll.date.Date;
import giantsweetroll.xml.dom.XMLManager;
import gui.ActionPanel;

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
			names[i] = data.get(i).getDisplayName();
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
			if (data.get(i).getDisplayName().equals(displayName))
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
}
