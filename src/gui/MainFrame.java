package gui;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import constants.Constants;
import constants.FontConstants;
import constants.FramePanelConstants;
import giantsweetroll.GMisc;
import gui.alokasi.InputAlokasi;
import gui.methods.FileOperation;
import gui.methods.Methods;
import gui.search.SearchFilterItem;
import gui.search.SearchFilterPanel;
import stok.InputStock;

public class MainFrame
{
	public static JFrame frame;
	private static JPanel panel;
	
	private MainMenu mainMenu;
	private OverviewPanel stockOverview, alokasiOverview;
	private InputStock stockInput;
	private InputAlokasi alokasiInput;
	private SearchFilterPanel alokasiFilter;
	
	public MainFrame()
	{
		//Initialization
		frame = new JFrame();
		panel = new JPanel();
		this.initComponents();
		
		//Properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((GMisc.getScreenSize().width/4)*3, (GMisc.getScreenSize().height/4)*3);
		frame.setLocationRelativeTo(null);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
		panel.setLayout(new CardLayout());
		
		//Add to panel
		panel.add(this.mainMenu, FramePanelConstants.MAIN_MENU);
		panel.add(this.stockOverview, FramePanelConstants.STOCK_OVERVIEW);
		panel.add(this.stockInput, FramePanelConstants.STOCK_INPUT);
		panel.add(this.alokasiOverview, FramePanelConstants.ALOKASI_OVERVIEW);
		panel.add(this.alokasiInput, FramePanelConstants.ALOKASI_INPUT);
		
		//Add to frame
		frame.add(panel);
	}
	
	//Methods
	public void initComponents()
	{
		//Initialization
		this.mainMenu = new MainMenu();
		this.stockOverview = new OverviewPanel("Stok", 
												FramePanelConstants.STOCK_INPUT,
												Methods.getDataForTable(FileOperation.loadStock()), 
												Methods.createTableHeaderWithActionCell(Constants.STOCK_TABLE_HEADERS));
		this.stockInput = new InputStock();
		this.alokasiOverview = new OverviewPanel("Alokasi",
													FramePanelConstants.ALOKASI_INPUT,
													Methods.getDataForTable(FileOperation.loadAlokasi()),
													Methods.createTableHeaderWithActionCell(Constants.ALOKASI_TABLE_HEADERS));
		this.alokasiFilter = new SearchFilterPanel();
		this.alokasiInput = new InputAlokasi();
		
		//Properties
		this.alokasiFilter.addFilter(new SearchFilterItem("Program", SearchFilterItem.PROGRAM, new String[1]));
		this.alokasiFilter.addFilter(new SearchFilterItem("Area", SearchFilterItem.AREA, new String[1]));
		this.alokasiFilter.addFilter(new SearchFilterItem("Site", SearchFilterItem.SITE, new String[1]));
		this.alokasiFilter.addFilter(new SearchFilterItem("PIC", SearchFilterItem.PIC, new String[1]));
		this.alokasiOverview.setSearchFilterPanel(this.alokasiFilter);
	}
	
	public static void changePanel(String keyword)
	{
		CardLayout cl = (CardLayout)panel.getLayout();
		cl.show(panel, keyword);
	}
	
	public static void main(String args[])
	{
		GMisc.setUIFont(FontConstants.FONT_NAME_GENERAL, Font.PLAIN, FontConstants.FONT_SIZE_GENERAL);
		SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					new MainFrame();
					frame.setVisible(true);
				}
		});
	}
}
