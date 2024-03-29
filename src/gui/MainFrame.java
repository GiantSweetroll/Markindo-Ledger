package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import constants.Constants;
import constants.FontConstants;
import constants.FramePanelConstants;
import constants.Globals;
import constants.URLConstants;
import giantsweetroll.GGUtilities;
import giantsweetroll.ImageManager;
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
		frame = new JFrame("Markindo Ledger");
		panel = new JPanel();
		this.initComponents();
		
		//Properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((GGUtilities.getScreenSize().width/4)*3, (GGUtilities.getScreenSize().height/4)*3);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(ImageManager.getImage(URLConstants.MARKINDO_LOGO));
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
		UIManager.put("Panel.background", Constants.PANEL_COLOR);
		UIManager.put("Label.foreground", Color.WHITE);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.font", FontConstants.GENERAL_FONT_BOLD);
//		UIManager.put("Button.background", new Color(192, 195, 198));
		UIManager.put("Button.background", Constants.BUTTON_COLOR);
		UIManager.put("TabbedPane.background", new Color (67, 70, 75));
		UIManager.put("TabbedPane.foreground", Color.WHITE);
		UIManager.put("TabbedPane.selected", new Color (64, 111, 151));
		UIManager.put("TabbedPane.tabsOverlapBorder", new Color(48, 58, 60));
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(1, 0, 1, 0));
		UIManager.put("RadioButton.foreground", Color.WHITE);
		UIManager.put("TitledBorder.titleColor", Color.WHITE);
		UIManager.put("TitledBorder.border", BorderFactory.createLineBorder(Constants.SECTION_BORDER_COLOR, 1));
		UIManager.put("TextField.background", Constants.TEXT_FIELD_ENABLED_COLOR);
		UIManager.put("TextField.foreground", Color.WHITE);
		UIManager.put("TextField.border", BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		UIManager.put("OptionPane.background", Constants.PANEL_COLOR);
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		
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
