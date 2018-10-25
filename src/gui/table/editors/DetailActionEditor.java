package gui.table.editors;

import java.util.List;

import javax.swing.JOptionPane;

import datadriver.DataDriver;
import gui.DetailPanel;

public class DetailActionEditor extends ActionButtonEditor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109312569870771733L;
	
	public DetailActionEditor(List<? extends DataDriver> data) 
	{
		super(data);
	}
	
	@Override
	public void clickedAction()
	{
		JOptionPane.showMessageDialog(null, new DetailPanel(this.getData().get(this.getSelectedIndex())), "", JOptionPane.PLAIN_MESSAGE, null);
	}
}
