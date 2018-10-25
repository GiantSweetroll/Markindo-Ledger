package gui.actionButton;

import javax.swing.JButton;

import datadriver.DataDriver;

public abstract class ActionButton extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8625409229104548862L;
	
	protected DataDriver data;

	public ActionButton(String text, DataDriver data)
	{
		super(text);
		this.data = data;
	}
	
	//public methods
	public DataDriver getLinkedData()
	{
		return this.data;
	}
	
	//Abstract methods
	public abstract void doAction();
}
