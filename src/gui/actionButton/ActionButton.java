package gui.actionButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import datadriver.DataDriver;

public abstract class ActionButton extends JButton implements ActionListener
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
		this.addActionListener(this);
	}
	
	//public methods
	public DataDriver getLinkedData()
	{
		return this.data;
	}
	
	//Abstract methods
	public abstract void doAction();
	
	//Interface
	public void actionPerformed(ActionEvent e)
	{
		this.doAction();
	}
}
