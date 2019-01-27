package gui.input;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SpringLayout;

import constants.Constants;
import giantsweetroll.filters.LongFilter;
import giantsweetroll.gui.swing.GTextField;
import methods.Methods;

public class InputAmount extends FormElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7536096637787602012L;

	private JTextField tf;
	
	//Constructor
	public InputAmount(String name)
	{
		super(name);
		//Initialization
		this.tf = new GTextField("", 20, new LongFilter(0L, Long.MAX_VALUE));
		SpringLayout layout = this.getLayout();
		
		//Properties
		this.tf.addActionListener(this);
		Methods.autoLayout(layout, this.tf, this.getTitleLabel(), this);
		this.tf.setCaretColor(Color.WHITE);
		
		//Add to panel
		this.add(this.tf);
	}
	
	//Overridden Methods
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
