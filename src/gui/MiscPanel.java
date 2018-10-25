package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import constants.FramePanelConstants;
import constants.Globals;
import interfaces.GUIMethods;

public class MiscPanel extends JPanel implements GUIMethods, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6864785518575965873L;

	private JButton butBack;
	private JPanel panelBelow, panelCenter;
	private JScrollPane scrollCenter;
	
	//Constructor
	public MiscPanel()
	{
		this.init();
	}
	
	//Create GUI
	public void init()
	{
		//Initialization
		this.initPanelBelow();
		this.initPanelCenter();
		this.scrollCenter = new JScrollPane(this.panelCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.scrollCenter, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	public void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		
		//Properties
		this.panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.panelCenter.setOpaque(false);
		
		//Add to panel
		this.panelCenter.add(Globals.picOverview);
		this.panelCenter.add(Globals.programOverview);
		this.panelCenter.add(Globals.siteOverview);
	}
	public void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butBack = new JButton("Kembali ke Menu");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panelBelow.setOpaque(false);
		this.butBack.addActionListener(this);
		
		//Add to panel
		this.panelBelow.add(this.butBack);
	}
	
	@Deprecated
	@Override
	public void resetDefaults() 
	{
		Globals.siteOverview.resetDefaults();
		Globals.programOverview.resetDefaults();
		Globals.picOverview.resetDefaults();
	}

	@Deprecated
	@Override
	public void refresh() 
	{
		Globals.siteOverview.refresh();
		Globals.programOverview.refresh();
		Globals.picOverview.refresh();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		MainFrame.changePanel(FramePanelConstants.MAIN_MENU);
	}
}
