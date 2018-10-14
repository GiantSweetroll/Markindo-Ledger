package gui.overviewpanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.AddDataPanel;
import gui.PageHyperlinkBar;
import gui.search.SearchFilterPanel;
import gui.table.OverviewTablePanel;

public abstract class OverviewPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 915590567046127765L;

	private PageHyperlinkBar link;
	private AddDataPanel panelAddAData;
	private SearchFilterPanel panelFilter;
	private OverviewTablePanel tablePanel;
	private JButton butRefresh;
	
	protected int itemNameLastIndex, programLastIndex, areaLastIndex, siteLastIndex, picLastIndex;
	
	//Constructors
	public OverviewPanel(String sectionName, String inputPanelConstant, JComponent[][] data, String[] tableHeaders)
	{
		//Initialization
		this.link = new PageHyperlinkBar(sectionName);
		this.panelAddAData = new AddDataPanel(inputPanelConstant);
		this.panelFilter = new SearchFilterPanel(this);
		this.tablePanel = new OverviewTablePanel(data, tableHeaders);
		this.butRefresh = new JButton("Refresh");
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		this.panelFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.butRefresh.addActionListener(this);
		
		//Add to panel
		this.add(this.link);
		this.add(this.panelAddAData);
		this.add(this.panelFilter);
		this.add(this.tablePanel);
		this.add(this.butRefresh);
	}
	
	//Methods
	public void setSearchFilterPanel(SearchFilterPanel searchFilterPanel)
	{
		this.remove(this.panelFilter);
		this.remove(this.tablePanel);
		this.panelFilter = searchFilterPanel;
		this.add(this.panelFilter);
		this.add(this.tablePanel);
		this.revalidate();
		this.repaint();
	}
	public OverviewTablePanel getOverviewTablePanel()
	{
		return this.tablePanel;
	}
	
	public SearchFilterPanel getSearchFilterPanel()
	{
		return this.panelFilter;
	}
	
	//Abstract Methods
	public abstract void refresh();
	public abstract void filter();
	
	//Interfaces
	public void actionPerformed(ActionEvent e)
	{
		this.refresh();
	}
}
