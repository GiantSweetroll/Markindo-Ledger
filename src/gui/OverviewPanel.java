package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.search.SearchFilterPanel;
import gui.table.OverviewTable;
import gui.table.OverviewTablePanel;

public class OverviewPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 915590567046127765L;

	private PageHyperlinkBar link;
	private AddDataPanel panelAddAData;
	private SearchFilterPanel panelFilter;
	private OverviewTablePanel tablePanel;
	
	//Constructors
	public OverviewPanel(String sectionName, String inputPanelConstant, JComponent[][] data, String[] tableHeaders)
	{
		//Initialization
		this.link = new PageHyperlinkBar(sectionName);
		this.panelAddAData = new AddDataPanel(inputPanelConstant);
		this.panelFilter = new SearchFilterPanel();
		this.tablePanel = new OverviewTablePanel(new OverviewTable(data, tableHeaders));
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		this.panelFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Add to panel
		this.add(this.link);
		this.add(this.panelAddAData);
		this.add(this.panelFilter);
		this.add(this.tablePanel);
	}
	
	//Methods
	public void setSearchFilterPanel(SearchFilterPanel searchFilterPanel)
	{
		this.panelFilter = searchFilterPanel;
		this.revalidate();
		this.repaint();
	}
}
