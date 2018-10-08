package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import datadriver.DataDriver;

public class ActionPanel extends JPanel implements ActionListener, MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3174647802050313187L;

	private JButton butInfo, butEdit, butDel;
	private DataDriver data;
	
	//Constants
	private final String INFO = "info",
							EDIT = "edit",
							DELETE = "delete";
	
	public ActionPanel(DataDriver data)
	{
		//Initialization
		this.butInfo = new JButton("Detil");
		this.butDel = new JButton("Hapus");
		this.butEdit = new JButton("Koreksi");
		this.data = data;
		
		//Properties
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setOpaque(false);
		this.butInfo.setActionCommand(this.INFO);
		this.butInfo.addActionListener(this);
		this.butInfo.addMouseListener(this);
		this.butDel.setActionCommand(this.DELETE);
		this.butDel.addActionListener(this);
		this.butDel.addMouseListener(this);
		this.butEdit.setActionCommand(this.EDIT);
		this.butEdit.addActionListener(this);
		this.butEdit.addMouseListener(this);
		
		//Add to panel
		this.add(this.butInfo);
		this.add(this.butEdit);
		this.add(this.butDel);
	}
	
	//Private Methods
	private void showInfo()
	{
		System.out.println("Hello World");
	}
	private void delete()
	{
		
	}
	private void edit()
	{
		
	}

	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case INFO:
				this.showInfo();
				break;
				
			case DELETE:
				this.delete();
				break;
				
			case EDIT:
				this.edit();
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		this.showInfo();
		if (e.getSource() == this.butInfo)
		{
			this.showInfo();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
