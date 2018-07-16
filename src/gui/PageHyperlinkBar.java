package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.FramePanelConstants;

public class PageHyperlinkBar extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5969255735457058627L;
	
	private JLabel label;
	private JButton butMenu;
	
	public PageHyperlinkBar(String page)
	{
		//Initialization
		this.label = new JLabel (" / " + page);
		this.butMenu = new JButton("Menu");
		
		//Properties
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setOpaque(false);
		this.butMenu.addActionListener(this);
		
		//Add to panel
		this.add(this.butMenu);
		this.add(this.label);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		MainFrame.changePanel(FramePanelConstants.MAIN_MENU);
	}
}
