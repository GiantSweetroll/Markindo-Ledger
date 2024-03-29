package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.FramePanelConstants;
import constants.URLConstants;
import giantsweetroll.ImageManager;

public class MainMenu extends JPanel implements ActionListener	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2586085524928657829L;
	
	private JPanel panelCenter, panelEntryButtons, panelBelow;
	private JButton butExit, butAloc, butSend, butStock;
	private JLabel labLogo;
	
	//Constants
	private final String EXIT = "exit", 
							ALOKASI = "alokasi",
							PENGIRIMAN = "pengiriman", 
							STOK = "stok",
							MISC = "misc";

	//Constructors
	public MainMenu()
	{
		this.init();
	}
	//Create GUI
	private void init()
	{
		//Initialization
		this.labLogo = new JLabel(ImageManager.getImageIcon(URLConstants.MARKINDO_LOGO_2));
		this.initPanelCenter();
		this.initPanelBelow();
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.labLogo, BorderLayout.NORTH);
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butExit = new JButton("Exit");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.panelBelow.setOpaque(false);
		this.butExit.setActionCommand(this.EXIT);
		this.butExit.addActionListener(this);
		this.butExit.setMnemonic(KeyEvent.VK_E);
		
		//Add to panel
		this.panelBelow.add(this.butExit);
	}
	private void initPanelEntryButtons()
	{
		//Initialization
		this.panelEntryButtons = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1324024801522805199L;

			@Override
			public Dimension getMaximumSize()			//Overridden to prevent height from being resized when component resizes
			{
				Dimension original = super.getMaximumSize();
				
				return new Dimension(original.width, super.getPreferredSize().height);
			}
		};
		this.butAloc = new JButton("Alokasi");
		this.butSend = new JButton("Pengiriman");
		this.butStock = new JButton("Stok");
		
		//Properties
		this.panelEntryButtons.setLayout(new GridLayout(1, 0, 20, 20));
		this.panelEntryButtons.setOpaque(false);
		this.butAloc.setActionCommand(this.ALOKASI);
		this.butAloc.addActionListener(this);
		this.butAloc.setMnemonic(KeyEvent.VK_A);
		this.butSend.setActionCommand(this.PENGIRIMAN);
		this.butSend.addActionListener(this);
		this.butSend.setMnemonic(KeyEvent.VK_P);
		this.butStock.setActionCommand(this.STOK);
		this.butStock.addActionListener(this);
		this.butStock.setMnemonic(KeyEvent.VK_S);
		
		//Add to panel
		this.panelEntryButtons.add(this.butAloc);
		this.panelEntryButtons.add(this.butSend);
		this.panelEntryButtons.add(this.butStock);
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.initPanelEntryButtons();
		GridBagConstraints c = new GridBagConstraints();
		
		//properties
		this.panelCenter.setLayout(new GridBagLayout());
		this.panelCenter.setOpaque(false);
		
		//Add to panel
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.panelCenter.add(this.panelEntryButtons, c);		//Main Menu Buttons
	}
	
	//Interfaces
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case EXIT:
				System.exit(0);
				break;
				
			case ALOKASI:
				MainFrame.changePanel(FramePanelConstants.ALOKASI_OVERVIEW);
				break;
			
			case PENGIRIMAN:
				MainFrame.changePanel(FramePanelConstants.PENGIRIMAN_OVERVIEW);
				break;
				
			case STOK:
				MainFrame.changePanel(FramePanelConstants.STOCK_OVERVIEW);
				break;
				
			case MISC:
				MainFrame.changePanel(FramePanelConstants.MISC);
				break;
		}
	}
}
