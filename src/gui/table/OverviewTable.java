package gui.table;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTable;

import constants.Constants;

public class OverviewTable extends JTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5539384614204097615L;

	private String[][] components;
	
	public OverviewTable(String[][] data, String[] columns)
	{
		super(data, columns);
//		this.setModel(new CustomTableModel(data, columns));
		
		this.components = data;
		
		//Table properties
//		this.getTableHeader().setBackground(new Color (8, 243, 240));			//Set Header color background
		this.getTableHeader().setBackground(Constants.TABLE_HEADER_COLOR);
		this.getTableHeader().setForeground(Color.WHITE);
		this.getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
		this.setBackground(Color.WHITE);
		this.setAutoCreateRowSorter(true);						//Automatically create Row sorter
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);			//Set to manual column size, so it works with the JScrollPane
		
		for (int i=0; i<this.getColumnCount()-1; i++)
		{
			this.getColumnModel().getColumn(i).setMinWidth(this.getColumnName(i).length());
		}
		
		//Center align headers
//		JLabel headerLabel = ((JLabel)this.getTableHeader().getDefaultRenderer());
//		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Makes the height the same as the button panel
		/*
		try
		{
			this.setRowHeight(data[0][data[0].length-1].getPreferredSize().height);
		}
		catch(ArrayIndexOutOfBoundsException ex) {}		*/
		
		//Center align cells data
		/*
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i=0; i<this.getColumnCount(); i++)
		{
			this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}*/
	}

	//Public Methods
	public String[][] getTableData()
	{
		return this.components;
	}
	
	//Override Methods
	@Override
	public boolean isCellEditable(int row, int column)		//Make the table data un-editable (except the buttons column)
	{
		if (column >= this.getColumnCount()-3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/*
	@Override
    public Class<?> getColumnClass(int column)
    {
		return getValueAt(0, column).getClass();
    }		*/
	/*
	@Override
	public Component prepareRenderer(TableCellRenderer r, int row, int column)
	{
		Component c = super.prepareRenderer(r, row, column);
		
		//Table row color patterns
		if (row%2==1)
		{
			//c.setBackground(Color.WHITE);
			//c.setBackground(new Color(101, 214, 214));		//Cyan blue
			c.setBackground(new Color(222, 220, 220));
		}
		else
		{
			c.setBackground(Color.LIGHT_GRAY);
			//c.setBackground(new Color(0, 45, 255));			//Lapis Blue
		}
		
		//Different color for selected row(s)
		
		if (super.isCellSelected(row, column))
		{
			//c.setBackground(new Color (8, 243, 240));		//Cyan Blue
			c.setBackground(new Color(77, 72, 72));
//			c.setForeground(Color.WHITE);
		}
		else
		{
			c.setForeground(Color.BLACK);
		}
		
		return c;
	}
	*/
	@Override
	public boolean getScrollableTracksViewportWidth()			//Resizes the table cells width to its preferred size or the viewport size, whichever is greater
	{
		return getPreferredSize().width < getParent().getWidth();
	}
	/*
	@Override
	public TableCellRenderer getCellRenderer(int row, int col)
	{
		return this;
	}

	
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		 return (Component) arg1;
	}		
	@Override
	public Object getValueAt(int row, int count) {
		return this.components[row][count];
	}*/
}
