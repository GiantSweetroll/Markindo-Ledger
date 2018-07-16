package input;

import javax.swing.JTextArea;

import giantsweetroll.gui.swing.TextAreaManager;

public class InputLongText extends FormElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -43193295659835439L;

	private JTextArea ta;
	
	//Constructor
	public InputLongText(String name)
	{
		super(name);
		//Initialization
		this.ta = new JTextArea(15, 20);
		
		//Properties
		TextAreaManager.autoConfigureTextArea(this.ta, true);
		
		//Add to panel
		this.addComponent(this.ta);
	}
	
	//Overriden Methods
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		this.ta.setEnabled(enabled);
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
}
