package gui.actionButton;

import javax.swing.JOptionPane;

import datadriver.DataDriver;
import gui.DetailPanel;

public class DetailButton extends ActionButton
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7275544289559496506L;

	public DetailButton(DataDriver data)
	{
		super("Detil", data);
	}

	//Overridden Methods
	@Override
	public void doAction() 
	{
		JOptionPane.showMessageDialog(null, new DetailPanel(this.getLinkedData()), "", JOptionPane.PLAIN_MESSAGE, null);
	}
}
