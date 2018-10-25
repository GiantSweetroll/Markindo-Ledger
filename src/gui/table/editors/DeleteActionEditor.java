package gui.table.editors;

import java.util.List;

import javax.swing.JOptionPane;

import constants.Globals;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.PIC;
import datadriver.Pengiriman;
import datadriver.Program;
import datadriver.Site;
import datadriver.Stock;
import methods.FileOperation;

public class DeleteActionEditor extends ActionButtonEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1722839407012451568L;
	
	public DeleteActionEditor(List<? extends DataDriver> data) 
	{
		super(data);
	}
	
	@Override
	public void clickedAction()
	{
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda benar ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_CANCEL_OPTION);
		if (response == JOptionPane.YES_OPTION)
		{
			DataDriver data = this.getData().get(this.getSelectedIndex());
			FileOperation.delete(data);
			if(data instanceof Stock)
			{
				Globals.reloadStock();
				Globals.stockOverview.refresh();
			}
			else if (data instanceof Alokasi)
			{
				Globals.reloadAlokasi();
				Globals.alokasiOverview.refresh();
			}
			else if (data instanceof Pengiriman)
			{
				Globals.reloadPengiriman();
				Globals.pengirimanOverview.refresh();
			}
			else if (data instanceof PIC)
			{
				Globals.reloadPIC();
			}
			else if (data instanceof Program)
			{
				Globals.reloadProgram();
			}
			else if (data instanceof Site)
			{
				Globals.reloadSite();
			}
		}
	}
}
