package input;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import giantsweetroll.GMisc;

public class InputDropDownMenu extends FormElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2019725509813089625L;

	private JComboBox<String> combo;
	private String[] items;
	
	public InputDropDownMenu(String name, String[] items)
	{
		super(name);
		
		//Initialization
		this.combo = new JComboBox<String>(items);
		this.items = items;
		
		//Add to panel
		this.addComponent(this.combo);
	}
	
	//Methods
	public void setItems(String[] items)
	{
		this.items = items;
		this.refresh();
	}
	public String[] getItems()
	{
		return this.items;
	}
	
	//Overriden Methods
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		this.combo.setEnabled(enabled);
	}
	
	@Override
	public void resetDefaults()
	{this.combo.setSelectedIndex(0);}

	@Override
	public void refresh() 
	{
		this.combo.setModel(new DefaultComboBoxModel<String>(this.items));
		this.revalidate();
		this.repaint();
	}

	@Override
	public String getData()
	{return GMisc.getItem(this.combo).toString();}
	
	@Override
	public void setData(String text)
	{this.combo.setSelectedItem(text);}

	@Override
	public boolean isFilled()
	{
		return true;
	}
}
