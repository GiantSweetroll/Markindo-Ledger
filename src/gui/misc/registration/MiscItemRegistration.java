package gui.misc.registration;

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

	public MiscItemRegistration()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
	}
	
	public void addForm(FormElement form)
	{
		this.add(form);
	}
	
	public abstract boolean allFilled();
	
	public abstract DataDriver getData();
}
