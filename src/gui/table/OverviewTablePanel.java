package gui.table;

import java.awt.BorderLayout;

import javax.swing.JComponent;
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
	
	public OverviewTablePanel(JComponent[][] component, String[] headers)
	{
		//Initialization
		this.table = new OverviewTable(component, headers);
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
	public void refresh(JComponent[][] components, String[] headers)
	{
		this.table = new OverviewTable(components, headers);
		this.scroll.setViewportView(this.table);
		this.revalidate();
		this.repaint();
	}
	
	//Overridden Methods
	@Deprecated
	@Override
	public void resetDefaults() 
	{
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	@Override
	public void refresh() {}
}
