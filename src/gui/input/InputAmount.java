package gui.input;

import javax.swing.JTextField;

import giantsweetroll.filters.LongFilter;
import giantsweetroll.gui.swing.GTextField;

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
		this.tf = new GTextField("", 20, new LongFilter(1L, Long.MAX_VALUE));
		
		//Add to panel
		this.addComponent(this.tf);
	}
	
	//Overriden Methods
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		this.tf.setEnabled(enabled);
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
