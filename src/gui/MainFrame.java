package gui;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import constants.FontConstants;
import constants.FramePanelConstants;
import constants.Globals;
import giantsweetroll.GGUtilities;
import gui.alokasi.AlokasiOverview;
import gui.alokasi.InputAlokasi;
import gui.misc.oveview.PICOverviewPanel;
import gui.misc.oveview.ProgramOverviewPanel;
import gui.misc.oveview.SiteOverviewPanel;
import gui.misc.registration.PICRegistration;
import gui.misc.registration.ProgramRegistration;
import gui.misc.registration.SiteRegistration;
import gui.pengiriman.InputPengiriman;
import gui.pengiriman.PengirimanOverview;
import methods.Methods;
import stok.InputStock;
import stok.StockOverview;

public class MainFrame
{
	public static JFrame frame;
	private static JPanel panel;
	
	public MainFrame()
	{
		//Initialization
		frame = new JFrame();
		panel = new JPanel();
		this.initComponents();
		
		//Properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((GGUtilities.getScreenSize().width/4)*3, (GGUtilities.getScreenSize().height/4)*3);
		frame.setLocationRelativeTo(null);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
		panel.setLayout(new CardLayout());
		Globals.stockOverview.refresh();
		Globals.alokasiOverview.refresh();
		Globals.pengirimanOverview.refresh();
		
		//Add to panel
		panel.add(Globals.mainMenu, FramePanelConstants.MAIN_MENU);
		panel.add(Globals.stockOverview, FramePanelConstants.STOCK_OVERVIEW);
		panel.add(Globals.stockInput, FramePanelConstants.STOCK_INPUT);
		panel.add(Globals.alokasiOverview, FramePanelConstants.ALOKASI_OVERVIEW);
		panel.add(Globals.alokasiInput, FramePanelConstants.ALOKASI_INPUT);
		panel.add(Globals.miscPanel, FramePanelConstants.MISC);
		panel.add(Globals.pengirimanOverview, FramePanelConstants.PENGIRIMAN_OVERVIEW);
		panel.add(Globals.pengirimanInput, FramePanelConstants.PENGIRIMAN_INPUT);
		
		//Add to frame
		frame.add(panel);
	}
	
	//Methods
	public void initComponents()
	{
		//Initialization
		Globals.mainMenu = new MainMenu();
		Globals.stockOverview = new StockOverview();
		Globals.stockInput = new InputStock();
		Globals.alokasiOverview = new AlokasiOverview();
		Globals.alokasiInput = new InputAlokasi();
		Globals.pengirimanOverview = new PengirimanOverview();
		Globals.pengirimanInput = new InputPengiriman();
		Globals.registerPIC = new PICRegistration();
		Globals.registerProgram = new ProgramRegistration();
		Globals.registerSite = new SiteRegistration();
		Globals.picOverview = new PICOverviewPanel();
		Globals.programOverview = new ProgramOverviewPanel();
		Globals.siteOverview = new SiteOverviewPanel();
		Globals.miscPanel = new MiscPanel();
	}
	
	public static void changePanel(String keyword)
	{
		CardLayout cl = (CardLayout)panel.getLayout();
		cl.show(panel, keyword);
	}
	
	public static void main(String args[])
	{
		GGUtilities.setUIFont(FontConstants.FONT_NAME_GENERAL, Font.PLAIN, FontConstants.FONT_SIZE_GENERAL);
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
