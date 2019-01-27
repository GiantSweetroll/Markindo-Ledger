package gui.input;

import java.awt.Color;

import javax.swing.JTextArea;

import constants.Constants;
import giantsweetroll.gui.swing.TextAreaManager;

public class InputLongText extends FormElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -43193295659835439L;

	private JTextArea ta;
//	private JScrollPane scroll;
	
	//Constructor
	public InputLongText(String name)
	{
		//Initialization
		super(name);
		this.ta = new JTextArea(15, 20);
//		this.scroll = ScrollPaneManager.generateDefaultScrollPane(this.ta, 10, 10);
		
		//Properties
		TextAreaManager.autoConfigureTextArea(this.ta, true);
//		Methods.autoLayout(this.getLayout(), scroll, this.getTitleLabel(), this);
		
		//Add to panel
		this.addComponent(this.ta);
	}
	
	//Overridden Methods
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		this.ta.setEditable(enabled);
		if (enabled)
		{
			this.ta.setBackground(Color.WHITE);
		}
		else
		{
			this.ta.setBackground(Constants.DISABLED_COLOR);
		}
	}
	
	@Override
	public void resetDefaults()
	{this.ta.setText("");}

	@Override
	public void refresh() 
	{this.resetDefaults();}

	@Override
	public String getData()
	{return this.ta.getText().trim();}
	
	@Override
	public void setData(String text)
	{this.ta.setText(text);}

	@Override
	public boolean isFilled() {
		return !this.ta.getText().trim().equals("");
	}
}
