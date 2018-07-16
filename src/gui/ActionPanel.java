package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import datadriver.DataDriver;

public class ActionPanel extends JPanel implements ActionListener
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
		this.butDel.setActionCommand(this.DELETE);
		this.butDel.addActionListener(this);
		this.butEdit.setActionCommand(this.EDIT);
		this.butEdit.addActionListener(this);
		
		//Add to panel
		this.add(this.butInfo);
		this.add(this.butEdit);
		this.add(this.butDel);
	}

	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case INFO:
				System.out.println("Hello World");
				break;
				
			case DELETE:
				break;
				
			case EDIT:
				break;
		}
	}
}
