package gui.input;

import javax.swing.JTextField;

import methods.Methods;

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
		Methods.autoLayout(this.getLayout(), this.tf, this.getTitleLabel(), this);
		
		//Add to panel
		this.add(this.tf);
	}
	
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
