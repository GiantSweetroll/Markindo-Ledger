package gui.search;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import constants.SearchFilterConstants;

public class SearchFilterOptions extends JComboBox<String>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4445485145641077419L;

	protected SearchFilterOptions(String[] items)
	{
		super(initOptions(items));
	}
	
	private static String[] initOptions(String[] items)
	{
		String[] arr = new String[items.length+1];
		arr[0] = SearchFilterConstants.ALL;
		for (int i=1; i<arr.length; i++)
		{
			arr[i] = items[i-1];
		}
		
		return arr;
	}
	public void setItems(String[] items)
	{
		this.setModel(new DefaultComboBoxModel<String>(items));
	}
}
