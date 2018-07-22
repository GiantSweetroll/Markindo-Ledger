package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.search.SearchFilterPanel;
import gui.table.OverviewTablePanel;

public class OverviewPanel extends JPanel implements ActionListener
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
	private JComponent[][] tableData;
	private String[] tableHeaders;
	
	//Constructors
	public OverviewPanel(String sectionName, String inputPanelConstant, JComponent[][] data, String[] tableHeaders)
	{
		//Initialization
		this.tableData = data;
		this.tableHeaders = tableHeaders;
		this.link = new PageHyperlinkBar(sectionName);
		this.panelAddAData = new AddDataPanel(inputPanelConstant);
		this.panelFilter = new SearchFilterPanel();
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
	public void refresh(JComponent[][] components, String[] headers)
	{
		this.tableData = components;
		this.tableHeaders = headers;
		this.tablePanel.refresh(components, headers);
	}
	public void refresh()
	{
		this.tablePanel.refresh(this.tableData, this.tableHeaders);
	}
	
	//Interfaces
	public void actionPerformed(ActionEvent e)
	{
		this.refresh();
	}
}
