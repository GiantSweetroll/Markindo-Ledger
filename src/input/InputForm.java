package input;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import datadriver.DataDriver;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.message.MessageManager;
import gui.MainFrame;
import gui.OverviewPanel;
import gui.methods.FileOperation;
import interfaces.GUIMethods;

public abstract class InputForm extends JPanel implements ActionListener, GUIMethods
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4836149291860656963L;
	
	private JLabel labName;
	private JPanel panelCenter, panelBelow;
	private JButton butSave, butCancel;
	private List<FormElement> formElements;
	private String prevPanelName, nextPanelName;
	private JScrollPane scroll;
	private boolean newEntry;
	private OverviewPanel overviewPanel;
	
	//Constants
	private final String CANCEL = "cancel";
	private final String SAVE = "save";
	
	//Constructor
	public InputForm(String formName, String prevPanelName, String nextPanelName, OverviewPanel overviewPanel)
	{
		this.overviewPanel = overviewPanel;
		this.init(formName, prevPanelName, nextPanelName);
	}
	//Create GUI
	private void init(String formName, String prevPanelName, String nextPanelName)
	{
		//Initialization
		this.initPanelBelow();
		this.initPanelCenter(formName);
		this.scroll = ScrollPaneManager.generateDefaultScrollPane(this.panelCenter, 10, 10);
		this.formElements = new ArrayList<FormElement>();
		this.prevPanelName = prevPanelName;
		this.nextPanelName = nextPanelName;
		this.setNewEntry(true);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Add to panel
		this.add(this.scroll, BorderLayout.CENTER);
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
	private void initPanelCenter(String formName)
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.labName = new JLabel(formName);
		
		//Properties
		this.panelCenter.setLayout(new BoxLayout(this.panelCenter, BoxLayout.Y_AXIS));
		this.panelCenter.setOpaque(false);
		
		//Add to panel
		this.panelCenter.add(this.labName);
	}
	
	//Methods
	public void addFormElement(FormElement element)
	{
		this.formElements.add(element);
		this.panelCenter.add(element);
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
	public abstract void refreshTable();
	
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
				DataDriver data = this.getData();
				
				if (FileOperation.dataExistsInDirectory(data))
				{
					if (this.isNewEntry())
					{
						MessageManager.showErrorDialog("Data sudah terdaftar. Silahkan coba lagi.", "Data sudah ada");
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
				break;
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
