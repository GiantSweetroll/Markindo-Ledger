package gui.input;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.InsetsConstants;
import interfaces.GUIMethods;

public abstract class FormElement extends JPanel implements GUIMethods
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4780997202916132569L;
	
	private JLabel labName;
	private GridBagConstraints c = new GridBagConstraints();
	
	public FormElement(String elementName)
	{
		//Initialization
		this.labName = new JLabel(elementName, SwingConstants.LEFT);
		
		//Properties
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);
		
		//Add to panel
		c.insets = InsetsConstants.GENERAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.add(this.labName, c);
	}
	
	//Methods
	public void addComponent(JComponent component)
	{
		this.add(component, c);
	}
	public abstract boolean isFilled();
	public abstract String getData();
	public abstract void setData(String data);
}
