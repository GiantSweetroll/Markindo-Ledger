package gui;

import javax.swing.JLabel;

import constants.FontConstants;

public class BoldedLabel extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6285173005131392045L;
	
	//Constructors
	public BoldedLabel()
	{
		super();
		this.init();
	}
	public BoldedLabel(String str)
	{
		super(str);
		this.init();
	}
	public BoldedLabel(String str, int orientation)
	{
		super(str, orientation);
		this.init();
	}
	
	//Private methods
	private void init()
	{
		this.setFont(FontConstants.GENERAL_FONT_BOLD);
	}
}
