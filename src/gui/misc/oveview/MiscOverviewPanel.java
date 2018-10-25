package gui.misc.oveview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import datadriver.DataDriver;
import giantsweetroll.gui.swing.ScrollPaneManager;
import gui.misc.registration.MiscItemRegistration;
import gui.table.OverviewTablePanel;
import interfaces.GUIMethods;

public abstract class MiscOverviewPanel extends JPanel implements GUIMethods, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849167022933736649L;

	private JButton butAdd, butRefresh;
	private JLabel label;
	private OverviewTablePanel overview;
	private JPanel panelBelow;
	private JScrollPane scroll;
	private MiscItemRegistration reg;
	
	//Constants
	private final String ADD = "add",
							REFRESH = "refresh";
	
	//Constructor
	public MiscOverviewPanel(String name, List<? extends DataDriver> components, String[] headers, MiscItemRegistration reg)
	{
		//Initialization
		this.initPanelBelow();
		this.label = new JLabel(name, SwingConstants.CENTER);
		this.overview = new OverviewTablePanel(components, headers);
		this.reg = reg;
		this.scroll = ScrollPaneManager.generateDefaultScrollPane(this.overview, 10, 10);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.label, BorderLayout.NORTH);
		this.add(this.scroll, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	
	//Create GUI
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butAdd = new JButton("Tambah");
		this.butRefresh = new JButton("Refresh");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		this.butAdd.addActionListener(this);
		this.butAdd.setActionCommand(this.ADD);
		this.butRefresh.setActionCommand(this.REFRESH);
		this.butRefresh.addActionListener(this);
		
		//Add to panel
		this.panelBelow.add(this.butRefresh);
		this.panelBelow.add(this.butAdd);
	}
	
	//Public Methods
	public MiscItemRegistration getRegistrationForm()
	{
		return this.reg;
	}
	protected void refresh(List<? extends DataDriver> components, String[] headers)
	{
		this.overview.refresh(components, headers);
		this.scroll.setViewportView(this.overview);
	}
	protected OverviewTablePanel getOveviewTablePanel()
	{
		return this.overview;
	}
	
	//Abstract Methods
	public abstract void saveData();
	protected abstract void registerMiscItem();
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case ADD:
				this.registerMiscItem();
				break;
				
			case REFRESH:
				this.refresh();
				break;
		}
	}
}
