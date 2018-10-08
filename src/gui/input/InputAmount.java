package gui.input;

import javax.swing.JTextField;
import javax.swing.SpringLayout;

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
		
		//Add to panel
		this.add(this.tf);
	}
	
	//Overridden Methods
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
