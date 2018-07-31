package gui.input;

import javax.swing.JTextField;

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
		super(name);
		
		//Initialization
		this.tf = new JTextField("", 20);
		
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
