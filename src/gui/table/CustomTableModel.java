package gui.table;

import javax.swing.JComponent;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1780823641175579655L;

	private JComponent[][] components;
	
	public CustomTableModel(JComponent[][] components)
	{
		this.components = components;
	}
	
	//Overridden Methods
	@Override
	public int getColumnCount() 
	{
		try
		{
			return this.components[0].length;
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			return 0;
		}
	}

	@Override
	public int getRowCount() 
	{
		return this.components.length;
	}

	@Override
	public Object getValueAt(int row, int count) {
		return this.components[row][count];
	}
	
	@Override
	public boolean isCellEditable(int row, int column)		//Make the table data un-editable (except the buttons column)
	{
		return true;
	}

}
