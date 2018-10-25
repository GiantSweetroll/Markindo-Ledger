package gui.actionButton;

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

public class DeleteButton extends ActionButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1422740938843103072L;

	public DeleteButton(DataDriver data)
	{
		super("Hapus", data);
	}

	//Overridden Methods
	@Override
	public void doAction()
	{
		int response = JOptionPane.showConfirmDialog(null, "Apakah anda benar ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_CANCEL_OPTION);
		if (response == JOptionPane.YES_OPTION)
		{
			FileOperation.delete(this.getLinkedData());
			if(this.getLinkedData() instanceof Stock)
			{
				Globals.reloadStock();
				Globals.stockOverview.refresh();
			}
			else if (this.getLinkedData() instanceof Alokasi)
			{
				Globals.reloadAlokasi();
				Globals.alokasiOverview.refresh();
			}
			else if (this.getLinkedData() instanceof Pengiriman)
			{
				Globals.reloadPengiriman();
				Globals.pengirimanOverview.refresh();
			}
			else if (this.getLinkedData() instanceof PIC)
			{
				Globals.reloadPIC();
			}
			else if (this.getLinkedData() instanceof Program)
			{
				Globals.reloadProgram();
			}
			else if (this.getLinkedData() instanceof Site)
			{
				Globals.reloadSite();
			}
		}
	}
}
