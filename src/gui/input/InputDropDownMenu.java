package gui.input;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;

import methods.Methods;
import renderers.ComboBoxObjectRenderer;

public class InputDropDownMenu extends FormElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2019725509813089625L;

	private JComboBox<Object> combo;
	private Object[] items;
	
	public InputDropDownMenu(String name, Object[] items)
	{
		super(name);
		
		//Initialization
		this.combo = new JComboBox<Object>(items);
		this.items = items;
		SpringLayout layout = this.getLayout();
		
		//Properties
		this.combo.addActionListener(this);
		this.combo.setRenderer(new ComboBoxObjectRenderer());
		Methods.autoLayout(layout, this.combo, this.getTitleLabel(), this);
		
		//Add to panel
		this.add(this.combo);
	}
	
	//Methods
	public void setItems(Object[] items)
	{
		this.items = items;
		this.refresh();
	}
	public void setItems(List<Object> items)
	{
		this.setItems(items.toArray(new Object[items.size()]));
	}
	public Object[] getItems()
	{
		return this.items;
	}
	public void setSelectedIndex(int index)
	{
		this.combo.setSelectedIndex(index);
	}
	public int getSelectedIndex()
	{
		return this.combo.getSelectedIndex();
	}
	public Object getSelectedItem()
	{
		return this.combo.getSelectedItem();
	}
	public void setData(int index)
	{
		this.combo.setSelectedIndex(index);
	}
	
	//Overridden Methods
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
		this.combo.setModel(new DefaultComboBoxModel<Object>(this.items));
		this.revalidate();
		this.repaint();
	}

	@Override
	public Object getData()
	{return this.combo.getSelectedItem();}
	
	@Override
	public void setData(String text)
	{this.combo.setSelectedItem(text);}

	@Override
	public boolean isFilled()
	{
		return true;
	}
}
