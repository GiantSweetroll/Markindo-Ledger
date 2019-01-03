package gui.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import constants.InsetsConstants;
import interfaces.GUIMethods;

public abstract class FormElement extends JPanel implements GUIMethods, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4780997202916132569L;
	
	private JLabel labName;
	private SpringLayout layout;
	
	public FormElement(String elementName)
	{
		//Initialization
		this.labName = new JLabel(elementName, SwingConstants.LEFT);
		this.layout = new SpringLayout();
		
		//Properties
		this.setLayout(layout);
		this.setOpaque(false);
		layout.putConstraint(SpringLayout.NORTH, this.labName, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, this.labName, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, this.labName, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.WEST, this);
		
		//Add to panel
		this.add(this.labName);
	}
	
	//Public Methods
	public JLabel getTitleLabel()
	{
		return this.labName;
	}
	
	//Overridden Methods
	@Override
	public SpringLayout getLayout()
	{
		return this.layout;
	}
	
	//Interface
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	//Abstract Methods
	public abstract boolean isFilled();
	public abstract Object getData();
	public abstract void setData(String data);
}
