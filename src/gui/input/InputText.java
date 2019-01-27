package gui.input;

import java.awt.Color;

import javax.swing.JTextField;

import constants.Constants;

public class InputText extends FormElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422472785505528313L;

	private JTextField tf;
	
	//Constructor
	public InputText(String name)
	{
		//Initialization
		super(name);
		this.tf = new JTextField("", 20);
		
		//Properties
		this.tf.addActionListener(this);
//		Methods.autoLayout(this.getLayout(), this.tf, this.getTitleLabel(), this);
		this.tf.setCaretColor(Color.WHITE);
		
		//Add to panel
		this.addComponent(this.tf);
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		this.tf.setEnabled(enabled);
		if (!enabled)
		{
			this.tf.setBackground(Constants.TEXT_FIELD_DISABLED_COLOR);
		}
		else
		{
			this.tf.setBackground(Constants.TEXT_FIELD_ENABLED_COLOR);
		}
	}
	
	@Override
	public void resetDefaults()
	{this.tf.setText("");}

	@Override
	public void refresh() 
	{this.resetDefaults();}

	@Override
	public String getData()
	{return this.tf.getText().trim();}
	
	@Override
	public void setData(String text)
	{this.tf.setText(text);}

	@Override
	public boolean isFilled() 
	{
		return !this.tf.getText().trim().equals("");
	}
}
