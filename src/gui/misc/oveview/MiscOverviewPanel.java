package gui.misc.oveview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.misc.registration.MiscItemRegistration;
import gui.table.OverviewTable;
import gui.table.OverviewTablePanel;
import interfaces.GUIMethods;

public abstract class MiscOverviewPanel extends JPanel implements GUIMethods, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849167022933736649L;

	private JButton butAdd;
	private JLabel label;
	private OverviewTablePanel overview;
	private JPanel panelBelow;
	private MiscItemRegistration reg;
	
	//Constructor
	public MiscOverviewPanel(String name, OverviewTable table, MiscItemRegistration reg)
	{
		//Initialization
		this.initPanelBelow();
		this.label = new JLabel(name, SwingConstants.CENTER);
		this.overview = new OverviewTablePanel(table);
		this.reg = reg;
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.label, BorderLayout.NORTH);
		this.add(this.overview, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	
	//Create GUI
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butAdd = new JButton("Tambah");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		this.butAdd.addActionListener(this);
		
		//Add to panel
		this.panelBelow.add(this.butAdd);
	}
	
	//Public Methods
	public MiscItemRegistration getRegistrationForm()
	{
		return this.reg;
	}
	
	//Abstract Methods
	public abstract void saveData();
	
	//Interfaces
	@Override
	public void resetDefaults()
	{
		this.overview.resetDefaults();
	}
	@Override
	public void refresh()
	{
		this.overview.refresh();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		while(true)
		{
			if (JOptionPane.showConfirmDialog(null, this.reg, "", JOptionPane.YES_NO_CANCEL_OPTION) != JOptionPane.YES_OPTION)
			{
				break;
			}
			else
			{
				if (this.reg.allFilled())
				{
					this.saveData();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Mohon isi semua data dengan tepat");
				}
			}
		}
	}
}
