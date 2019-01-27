package gui.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import datadriver.DataDriver;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.message.MessageManager;
import gui.BannerPanel;
import gui.MainFrame;
import interfaces.GUIMethods;
import methods.FileOperation;
import methods.Methods;

public abstract class InputForm extends JPanel implements ActionListener, GUIMethods
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4836149291860656963L;
	
	private JLabel labName;
	private JPanel panelForm, panelBelow, panelCenter;
	private JButton butSave, butCancel;
	private List<FormElement> formElements;
	private String prevPanelName, nextPanelName;
	private JScrollPane scroll;
	private BannerPanel banner;
	private boolean newEntry;
	protected DataDriver oldData;
//	private GridBagConstraints c;
	
	//Constants
	private final String CANCEL = "cancel";
	private final String SAVE = "save";
	
	//Constructor
	public InputForm(String formName, String prevPanelName, String nextPanelName)
	{
		this.init(formName, prevPanelName, nextPanelName);
	}
	//Create GUI
	private void init(String formName, String prevPanelName, String nextPanelName)
	{
		//Initialization
		this.initPanelBelow();
		this.initPanelCenter(formName);
		this.banner = new BannerPanel();
		
		this.formElements = new ArrayList<FormElement>();
		this.prevPanelName = prevPanelName;
		this.nextPanelName = nextPanelName;
		this.setNewEntry(true);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.banner, BorderLayout.NORTH);
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butCancel = new JButton("Batal");
		this.butSave = new JButton("Simpan");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		this.panelBelow.setOpaque(false);
		this.butCancel.setActionCommand(this.CANCEL);
		this.butSave.setActionCommand(this.SAVE);
		this.butCancel.addActionListener(this);
		this.butSave.addActionListener(this);
		
		//Add to panel
		this.panelBelow.add(this.butCancel);
		this.panelBelow.add(this.butSave);
	}
	private void initPanelForm(String formName)
	{
		//Initialization
		this.panelForm = new JPanel();
		this.labName = new JLabel(" " + formName);
//		this.c = new GridBagConstraints();
		
		//Properties
		this.panelForm.setLayout(new BoxLayout(this.panelForm, BoxLayout.Y_AXIS));
//		this.panelForm.setLayout(new GridLayout(0, 1));
//		this.panelCenter.setLayout(new GridBagLayout());
//		this.panelForm.setBackground(Color.white);
		this.panelForm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.labName.setFont(new Font("calibri", Font.BOLD, 20));
		
		//Add to panel
	//	Gbm.goToOrigin(c);
	//	this.panelCenter.add(this.labName, c);
	//	Gbm.newGridLine(c);
		this.panelForm.add(this.labName);
	}
	private void initPanelCenter(String formName)
	{
		//Initialization
		this.panelCenter = new JPanel(new GridLayout(1, 0));
		this.initPanelForm(formName);
		this.scroll = ScrollPaneManager.generateDefaultScrollPane(this.panelForm, 10, 10);
		
		//Add to panel
		this.panelCenter.add(new JPanel());
		this.panelCenter.add(this.scroll);
		this.panelCenter.add(new JPanel());
	}
	
	//Methods
	public void addFormElement(FormElement element)
	{
		this.formElements.add(element);
		this.panelForm.add(element);
		this.panelForm.revalidate();
		this.panelForm.repaint();
	//	this.panelCenter.add(element, c);
	//	Gbm.newGridLine(c);
	}
	public boolean isNewEntry()
	{
		return this.newEntry;
	}
	public void setNewEntry(boolean bool)
	{
		this.newEntry = bool;
	}
	
	//Abstract methods
	public abstract DataDriver getData();
	public abstract void setData(DataDriver data);
	public abstract boolean allFilled();
	public abstract void savingDataStarting();
	public abstract void savingDataClosing();
	public abstract boolean canExport();
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CANCEL:
				MainFrame.changePanel(prevPanelName);
				break;
				
			case SAVE:
				if (this.canExport())
				{
					this.savingDataStarting();
					DataDriver data = this.getData();
					
					if (FileOperation.dataExistsInDirectory(data))
					{
						if (this.isNewEntry())
						{
							MessageManager.showErrorDialog("Data sudah terdaftar. Silahkan coba lagi.", "Data sudah ada");
							break;
						}
						else
						{
							FileOperation.exportData(data);
							MainFrame.changePanel(nextPanelName);
						}
					}
					else
					{
						FileOperation.exportData(data);
						MainFrame.changePanel(nextPanelName);
					}
					
					Methods.reloadGlobalVariables();
					this.savingDataClosing();
					break;
				}
		}
	}
	
	@Override
	public void resetDefaults() 
	{
		for (FormElement element : this.formElements)
		{
			element.resetDefaults();
		}
	}
	
	@Override
	public void refresh() 
	{
		for (FormElement element : this.formElements)
		{
			element.refresh();
		}
	}
}
