package gui.methods;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import constants.Constants;
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
}
