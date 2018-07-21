package gui.misc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.FramePanelConstants;
import gui.MainFrame;
import gui.misc.oveview.MiscOverviewPanel;
import gui.misc.oveview.SiteOverviewPanel;
import interfaces.GUIMethods;

public class MiscPanel extends JPanel implements GUIMethods, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6864785518575965873L;

	private JButton butBack;
	private JPanel panelBelow, panelCenter;
	private MiscOverviewPanel miscSite, miscPIC, miscProgram;
	
	//Constructor
	public MiscPanel()
	{
		this.init();
	}
	
	//Create GUI
	public void init()
	{
		
	}
	public void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.miscSite = new SiteOverviewPanel();
		
		//Properties
		this.panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panelCenter.setOpaque(false);
		
		//Add to panel
		
	}
	public void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butBack = new JButton("Kembali ke Menu");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panelBelow.setOpaque(false);
		
		//Add to panel
		this.panelBelow.add(this.butBack);
	}
	
	@Override
	public void resetDefaults() 
	{
	}

	@Override
	public void refresh() 
	{
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		MainFrame.changePanel(FramePanelConstants.MAIN_MENU);
	}
}
