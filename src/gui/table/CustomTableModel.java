package gui.table;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1780823641175579655L;

	private String[][] components;
	private String[] headers;
	
	public CustomTableModel(String[][] components, String[] headers)
	{
		this.components = components;
		this.headers = headers;
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
	public String getColumnName(int column)
	{
		return this.headers[column];
	}
}
