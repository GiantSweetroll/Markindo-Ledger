package gui.table;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import datadriver.DataDriver;
import giantsweetroll.gui.swing.table.renderers.JButtonRenderer;
import gui.table.editors.DeleteActionEditor;
import gui.table.editors.DetailActionEditor;
import gui.table.editors.EditActionEditor;
import methods.Methods;

public class OverviewTablePanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 58793642930522955L;

	private OverviewTable table;
	private JScrollPane scroll;
	private List<? extends DataDriver> rawData;
	private int activeIndex;
	
	public OverviewTablePanel(List<? extends DataDriver> rawData, String[] headers)
	{
		//Initialization
		this.activeIndex = -1;
		this.table = new OverviewTable(Methods.getDataForTable(rawData), headers);
		this.scroll = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.prepareTable();
		
		//Add to panel
		this.add(this.scroll, BorderLayout.CENTER);
	}
	
	//Private Methods
	private void prepareTable()
	{
		if (this.table.getTableData().length > 0)
		{
			//Apply button renderer
			for (int i=3; i>=1; i--)
			{
				this.table.getColumnModel().getColumn(this.table.getTableData()[0].length-i).setCellRenderer(new JButtonRenderer());
			}
			//Apply button editor
			this.table.getColumnModel().getColumn(this.table.getTableData()[0].length-3).setCellEditor(new DetailActionEditor(this.rawData));	
			this.table.getColumnModel().getColumn(this.table.getTableData()[0].length-2).setCellEditor(new EditActionEditor(this.rawData));
			this.table.getColumnModel().getColumn(this.table.getTableData()[0].length-1).setCellEditor(new DeleteActionEditor(this.rawData));
			
			this.table.getModel().addTableModelListener(new TableModelListener()
					{

						@Override
						public void tableChanged(TableModelEvent arg0) 
						{
							updateActiveIndex();
						}
				
					});
			
			this.table.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent e)
						{
							updateActiveIndex();
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mousePressed(MouseEvent e) {
							updateActiveIndex();
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
					});
		}
	}
	
	//Private Methods
	private void updateActiveIndex()
	{
		try
		{
			this.activeIndex = this.table.convertRowIndexToView(this.table.getSelectedRow());
		}
		catch(IndexOutOfBoundsException ex) {}
	}
	
	//Public Methods
	public List<? extends DataDriver> getRawData()
	{
		return this.rawData;
	}
	public OverviewTable getTable()
	{
		return this.table;
	}
	public JScrollPane getScrollPane()
	{
		return scroll;
	}
	public void refresh(List<? extends DataDriver> rawData, String[] headers)
	{
		this.rawData = rawData;
		this.table = new OverviewTable(Methods.getDataForTable(rawData), headers);
		this.prepareTable();
		this.scroll.setViewportView(this.table);
		this.revalidate();
		this.repaint();
	}
	public int getActiveSelectedIndex()
	{
		return this.activeIndex;
	}
}
