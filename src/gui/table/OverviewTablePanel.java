package gui.table;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaces.GUIMethods;

public class OverviewTablePanel extends JPanel implements GUIMethods
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 58793642930522955L;

	private OverviewTable table;
	private JScrollPane scroll;
	
	public OverviewTablePanel(OverviewTable table)
	{
		//Initialization
		this.table = table;
		this.scroll = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.scroll, BorderLayout.CENTER);
	}
	
	//Methods
	public OverviewTable getTable()
	{
		return this.table;
	}
	public JScrollPane getScrollPane()
	{
		return scroll;
	}
	
	//Overridden Methods
	@Override
	public void resetDefaults() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
