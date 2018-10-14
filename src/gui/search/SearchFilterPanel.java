package gui.search;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Globals;
import constants.SearchFilterConstants;
import datadriver.PIC;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import gui.overviewpanel.OverviewPanel;

public class SearchFilterPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 622265936866024007L;
	
	private JLabel labSearch;
	private JButton butSearch, butAll;
	private JPanel panelCenter, panelFilters;
	private LinkedHashMap<String, SearchFilterItem> filters;
	private OverviewPanel overview;
	
	//Constructor
	public SearchFilterPanel(OverviewPanel overview)
	{
		//Initialization
		this.overview = overview;
		this.labSearch = new JLabel("Pencarian");
		this.initPanelCenter();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.butAll.addActionListener(this);
		this.butSearch.addActionListener(this);
		
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
	//Public Methods
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
	public Stock getStockFilter()
	{
		try
		{
			return (Stock)this.getSelectedFilter(SearchFilterItem.STOCK);
		}
		catch(NullPointerException ex)
		{
			return null;
		}
	}
	public Program getProgramFilter()
	{
		try
		{
			return (Program)this.getSelectedFilter(SearchFilterItem.PROGRAM);
		}
		catch(NullPointerException ex)
		{
			return null;
		}
	}
	public String getAreaFilter()
	{
		try
		{
			return this.getSelectedFilter(SearchFilterItem.AREA).toString();
		}
		catch(NullPointerException ex)
		{
			return null;
		}
	}
	public Site getSiteFilter()
	{
		try
		{
			return (Site)this.getSelectedFilter(SearchFilterItem.SITE);
		}
		catch(NullPointerException ex)
		{
			return null;
		}
	}
	public PIC getPICFilter()
	{
		try
		{
			return (PIC)this.getSelectedFilter(SearchFilterItem.PIC);
		}
		catch(NullPointerException ex)
		{
			return null;
		}
	}
	public String getSenderFilter()
	{	
		try
		{	
			return this.getSelectedFilter(SearchFilterItem.SENDER).toString();
		}
		catch(NullPointerException ex)
		{
			return null;
		}
	}
	public SearchFilterItem getFilterObject(String key)
	{
		return this.filters.get(key);
	}
	
	//Private Methods
	private void initFilterList()
	{
		this.filters = new LinkedHashMap<String, SearchFilterItem>();
		
		this.filters.put(SearchFilterItem.STOCK, new SearchFilterItem("Nama Item", SearchFilterItem.STOCK, Globals.STOCKS.toArray(new Stock[Globals.STOCKS.size()])));
	}
	private Object getSelectedFilter(String key)
	{
		Object filter = this.filters.get(key).getSelectedFilter();
		if (filter instanceof String)
		{
			if (((String)filter).equals(SearchFilterConstants.ALL))
			{
				return null;
			}
			else
			{
				return filter;
			}
		}
		else
		{
			return filter;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.butAll)
		{
			for (Map.Entry<String, SearchFilterItem> entry : this.filters.entrySet())
			{
				entry.getValue().resetSelection();
			}
			this.overview.filter();
		}
		else if (e.getSource() == this.butSearch)
		{
			this.overview.filter();
		}
	}
}
