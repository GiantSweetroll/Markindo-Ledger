package gui.misc.registration;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import datadriver.DataDriver;
import gui.input.FormElement;
import interfaces.GUIMethods;

public abstract class MiscItemRegistration extends JPanel implements GUIMethods
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3478717736557027423L;

	protected DataDriver oldData;
	private boolean isNewEntry;
	private JPanel panel;
	
	public MiscItemRegistration()
	{
		//Initialization
		this.isNewEntry = false;
		this.initPanel();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
	
		//Add to panel
		this.add(this.panel, BorderLayout.CENTER);
	}
	//Create GUI
	private void initPanel()
	{
		//Initialization
		this.panel = new JPanel();
		
		//Properties
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
	}
	
	//Public methods
	public void addForm(FormElement form)
	{
		this.panel.add(form);
	}
	public boolean isNewEntry()
	{
		return this.isNewEntry;
	}
	public void setAsNewEntry(boolean newEntry)
	{
		this.isNewEntry = newEntry;
	}
	public DataDriver getOldData()
	{
		return this.oldData;
	}
	
	public abstract boolean allFilled();
	public abstract void setData(DataDriver data);
	public abstract DataDriver getData();
}
