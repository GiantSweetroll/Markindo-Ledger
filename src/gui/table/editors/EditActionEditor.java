package gui.table.editors;

import java.util.List;

import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.PIC;
import datadriver.Pengiriman;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import gui.MainFrame;
import methods.Methods;

public class EditActionEditor extends ActionButtonEditor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7158821521352581214L;
	
	public EditActionEditor(List<? extends DataDriver> data) 
	{
		super(data);
	}
	
	@Override
	public void clickedAction() 
	{
		DataDriver data = this.getData().get(this.getSelectedIndex());
		
		if (data instanceof Stock)
		{
			Globals.stockInput.setData(data);
			MainFrame.changePanel(FramePanelConstants.STOCK_INPUT);
		}
		else if (data instanceof Alokasi)
		{
			Globals.alokasiInput.setData(data);
			MainFrame.changePanel(FramePanelConstants.ALOKASI_INPUT);
		}
		else if (data instanceof Pengiriman)
		{
			Globals.pengirimanInput.setData(data);
			MainFrame.changePanel(FramePanelConstants.PENGIRIMAN_INPUT);
		}
		else if (data instanceof PIC)
		{
			Globals.registerPIC.setData(data);
			Methods.openMiscRegistrationForm(Globals.registerPIC);
		}
		else if (data instanceof Program)
		{
			Globals.registerProgram.setData(data);
			Methods.openMiscRegistrationForm(Globals.registerProgram);
		}
		else if (data instanceof Site)
		{
			Globals.registerSite.setData(data);
			Methods.openMiscRegistrationForm(Globals.registerSite);
		}
	}
}
