package renderers;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class ComboBoxObjectRenderer extends JLabel implements ListCellRenderer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1332461517153425652L;

	public ComboBoxObjectRenderer()
	{
		this.setOpaque(true);
		this.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) 
	{
//		int selectedIndex = ((Integer)value).intValue();		//Use this instead of the index from parameter
		
		
		if (isSelected)
		{
			this.setBackground(list.getSelectionBackground());
			this.setForeground(list.getSelectionForeground());
		}
		else
		{
			this.setBackground(list.getBackground());
			this.setForeground(list.getForeground());
		}
		
		
		//Set text name
		try
		{
			this.setText(value.toString());
		}
		catch(NullPointerException ex) {}
		
		return this;
	}

}
