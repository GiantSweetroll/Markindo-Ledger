package gui.search;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchFilterPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 622265936866024007L;
	
	private JLabel labSearch;
	private JButton butSearch, butAll;
	private JPanel panelCenter, panelFilters;
	private LinkedHashMap<String, SearchFilterItem> filters;
	
	//Constructor
	public SearchFilterPanel()
	{
		//Initialization
		this.labSearch = new JLabel("Pencarian");
		this.initPanelCenter();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.labSearch, BorderLayout.NORTH);
		this.add(this.panelCenter, BorderLayout.CENTER);
	}

	//GUI Methods
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.butAll = new JButton("Semua");
		this.butSearch = new JButton("Cari");
		this.initPanelFilters();
		
		//Properties
		this.panelCenter.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.panelCenter.setOpaque(false);
		
		//Add to panel
		this.panelCenter.add(this.panelFilters);
		this.panelCenter.add(this.butSearch);
		this.panelCenter.add(this.butAll);
	}
	private void initPanelFilters()
	{
		//Initialization
		this.panelFilters = new JPanel();
		this.initFilterList();
		
		//Properties
		this.panelFilters.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.panelFilters.setOpaque(false);
		
		//Add to panel
		for (Map.Entry<String, SearchFilterItem> entry : this.filters.entrySet())
		{
			this.panelFilters.add(entry.getValue());
		}
	}
	//Methods
	public void updateItem(String key, Object[] items)
	{
		this.filters.get(key).setItems(items);
	}
	public void addFilter(SearchFilterItem item)
	{
		this.filters.put(item.getKey(), item);
		this.panelFilters.add(item);
		this.panelFilters.revalidate();
		this.panelFilters.repaint();
	}
	private void initFilterList()
	{
		this.filters = new LinkedHashMap<String, SearchFilterItem>();
		
		this.filters.put(SearchFilterItem.ITEM_NAME, new SearchFilterItem("Nama Item", SearchFilterItem.ITEM_NAME, new String[1]));
	}
}
