package gui.actionButton;

import constants.FramePanelConstants;
import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.Pengiriman;
import datadriver.Stock;
import gui.MainFrame;

public class EditButton extends ActionButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264916732151020758L;

	public EditButton(DataDriver data)
	{
		super("Koreksi", data);
	}
	
	//Overridden Methods
	@Override
	public void doAction() 
	{
		DataDriver data = this.getLinkedData();
		
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
	}

}
