package gui.overviewpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import constants.Constants;
import constants.FontConstants;
import datadriver.DataDriver;
import gui.AddDataPanel;
import gui.BannerPanel;
import gui.PageHyperlinkBar;
import gui.search.SearchFilterPanel;
import gui.table.OverviewTablePanel;

public abstract class OverviewPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 915590567046127765L;

	private BannerPanel panelBanner;
	private PageHyperlinkBar link;
	private AddDataPanel panelAddAData;
	private SearchFilterPanel panelFilter;
	private OverviewTablePanel tablePanel;
	private JButton butRefresh;
	private JPanel panelTop, panelTopLeft;
	
	protected int itemNameLastIndex, programLastIndex, areaLastIndex, siteLastIndex, picLastIndex;
	
	//Constructors
	public OverviewPanel(String sectionName, String inputPanelConstant, List<? extends DataDriver> data, String[] tableHeaders)
	{
		//Initialization
		this.panelFilter = new SearchFilterPanel(this);
		this.tablePanel = new OverviewTablePanel(data, tableHeaders);
		this.butRefresh = new JButton("Refresh");
		this.initPanelTop(sectionName, inputPanelConstant);
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		this.setOpaque(false);
		this.setBackground(Constants.MENU_BAR_COLOR);
		this.panelFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.butRefresh.addActionListener(this);
		this.butRefresh.setMnemonic(KeyEvent.VK_R);
		
		//Add to panel
//		this.add(this.panelBanner);
//		this.add(this.link);
//		this.add(this.panelAddAData);
		this.add(this.panelTop);
		this.add(this.panelFilter);
		this.add(this.tablePanel);
		this.add(Box.createRigidArea(new Dimension(FontConstants.FONT_SIZE_GENERAL, FontConstants.FONT_SIZE_GENERAL)));
		this.add(this.butRefresh);
		this.add(Box.createRigidArea(new Dimension(FontConstants.FONT_SIZE_GENERAL, FontConstants.FONT_SIZE_GENERAL)));
	}
	private void initPanelTop(String sectionName, String inputPanelConstant)
	{
		//Initialization
		this.panelTop = new JPanel();
		this.panelBanner = new BannerPanel();
		this.initPanelTopLeft(sectionName, inputPanelConstant);
		
		//Properties
		this.panelTop.setLayout(new BorderLayout());
		this.panelTop.setOpaque(false);
		this.panelTop.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//		this.panelBanner.setOpaque(false);
		
		//add to panel
		this.panelTop.add(this.panelTopLeft, BorderLayout.WEST);
		this.panelTop.add(this.panelBanner, BorderLayout.EAST);
	}
	private void initPanelTopLeft(String sectionName, String inputPanelConstant)
	{
		//Initialization
		this.panelTopLeft = new JPanel();
		this.link = new PageHyperlinkBar(sectionName);
		this.panelAddAData = new AddDataPanel(inputPanelConstant);
		
		//Properties
		this.panelTopLeft.setLayout(new BoxLayout(this.panelTopLeft, BoxLayout.Y_AXIS));
		this.panelTopLeft.setOpaque(false);
		
		//Add to panel
		this.panelTopLeft.add(this.link);
		this.panelTopLeft.add(this.panelAddAData);
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
