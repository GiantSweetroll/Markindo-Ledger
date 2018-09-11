package gui;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import constants.FontConstants;
import constants.FramePanelConstants;
import giantsweetroll.GMisc;
import gui.alokasi.InputAlokasi;
import gui.overviewpanel.AlokasiOverview;
import gui.overviewpanel.StockOverview;
import gui.search.SearchFilterItem;
import gui.search.SearchFilterPanel;
import methods.Methods;
import stok.InputStock;

public class MainFrame
{
	public static JFrame frame;
	private static JPanel panel;
	
	public static MainMenu mainMenu;
	public static StockOverview stockOverview;
	public static AlokasiOverview alokasiOverview;
	public static InputStock stockInput;
	public static InputAlokasi alokasiInput;
	public static SearchFilterPanel alokasiFilter;
	public static MiscPanel miscPanel;
	
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
		panel.add(this.miscPanel, FramePanelConstants.MISC);
		
		//Add to frame
		frame.add(panel);
	}
	
	//Methods
	public void initComponents()
	{
		//Initialization
		this.mainMenu = new MainMenu();
		this.stockOverview = new StockOverview();
		this.stockInput = new InputStock();
		this.alokasiOverview = new AlokasiOverview();
		this.alokasiFilter = new SearchFilterPanel();
		this.alokasiInput = new InputAlokasi();
		this.miscPanel = new MiscPanel();
		
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
					//Initialize Global Variables
					Methods.reloadGlobalVariables();
					
					new MainFrame();
					frame.setVisible(true);
				}
		});
	}
}
