package gui.search;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.InsetsConstants;

public class SearchFilterItem extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5111112520064531602L;
	
	private JLabel label;
	private SearchFilterOptions options;
	private String key;
	
	//Constants
	public static final String STOCK = "stock",
								AREA = "area",
								SITE = "site",
								PROGRAM = "program",
								PIC = "pic",
								SENDER = "sender";
	
	public SearchFilterItem(String itemName, String key, Object[] items)
	{
		//Initialization
		this.label = new JLabel(itemName);
		this.options = new SearchFilterOptions(items);
		this.key = key;
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);
		
		//Add to panel
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = InsetsConstants.GENERAL;
		this.add(this.label, c);
		this.add(this.options, c);
	}
	
	//Methods
	public String getKey()
	{
		return this.key;
	}
	public void setItems(Object[] items)
	{
		this.options.setItems(items);
	}
	public int getSelectedIndex()
	{
		return this.options.getSelectedIndex();
	}
	public Object getSelectedFilter()
	{
		return this.options.getSelectedItem();
	}
	public void resetSelection()
	{
		this.options.setSelectedIndex(0);
	}
}
