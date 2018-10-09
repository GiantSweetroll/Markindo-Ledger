package gui.search;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import constants.SearchFilterConstants;

public class SearchFilterOptions extends JComboBox<Object>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4445485145641077419L;

	protected SearchFilterOptions(Object[] items)
	{
		super(initOptions(items));
	}
	
	private static Object[] initOptions(Object[] items)
	{
		Object[] arr = new Object[items.length+1];
		arr[0] = SearchFilterConstants.ALL;
		for (int i=1; i<arr.length; i++)
		{
			arr[i] = items[i-1];
		}
		
		return arr;
	}
	public void setItems(Object[] items)
	{
		this.setModel(new DefaultComboBoxModel<Object>(items));
	}
}
