package gui.table.editors;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;

import datadriver.DataDriver;
import giantsweetroll.gui.swing.table.editors.JButtonEditor;

public abstract class ActionButtonEditor extends JButtonEditor
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1632811786945948531L;
	
	protected List<? extends DataDriver> data;
	private int selectedIndex;
	
	public ActionButtonEditor(List<? extends DataDriver> data)
	{
		this.data = data;
		this.selectedIndex = -1;
	}
	
	//Protected methods
	protected List<? extends DataDriver> getData()
	{
		return this.data;
	}
	protected void setData(List<? extends DataDriver> data)
	{
		this.data = data;
	}
	protected int getSelectedIndex()
	{
		return this.selectedIndex;
	}
	
	//Overridden Methods
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) 
	{
//		this.selectedIndex = row;
		this.selectedIndex = table.convertRowIndexToModel(table.getSelectedRow());
		return super.getTableCellEditorComponent(table, obj, isSelected, row, column);
	}
}
