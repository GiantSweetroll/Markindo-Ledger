package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AddDataPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596408549319594080L;

	private JButton butAdd, butUpload;
	private JLabel labSeparator;
	private String form;
	
	//Constants
	private final String ADD = "add";
	private final String UPLOAD = "upload";
	
	//Constructors
	public AddDataPanel(String addForm)
	{
		//Initialization
		this.butAdd = new JButton("Tambah");
		this.butUpload = new JButton("Upload");
		this.labSeparator = new JLabel (" | ", SwingConstants.CENTER);
		this.form = addForm;
		
		//Properties
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setOpaque(false);
		this.butAdd.setActionCommand(this.ADD);
		this.butUpload.setActionCommand(this.UPLOAD);
		this.butAdd.addActionListener(this);
		this.butUpload.addActionListener(this);
		
		//Add to panel
		this.add(this.butAdd);
		this.add(this.labSeparator);
		this.add(this.butUpload);
	}
	
	//Interfaces
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case ADD:
				MainFrame.changePanel(this.form);
				break;
				
			case UPLOAD:
				break;
		}
	}
}
