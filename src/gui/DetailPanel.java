package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.FontConstants;
import constants.InsetsConstants;
import datadriver.Alokasi;
import datadriver.DataDriver;
import datadriver.Pengiriman;
import datadriver.Site;
import datadriver.Stock;
import giantsweetroll.gui.swing.Gbm;
import methods.Methods;

public class DetailPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7763168711272593487L;
	
	private JPanel panelCenter, panelTop;
	private JLabel labelTitle;
	private DataDriver data;
	private GridBagConstraints c;
	
	public DetailPanel(DataDriver data)
	{
		this.data = data;
		this.c = new GridBagConstraints();
		this.init();
	}
	
	//Create GUI
	private void initPanelTop()
	{
		//Initialization
		this.panelTop = new JPanel();
		String title = "Rincian ";
		if (data instanceof Stock)
		{
			title += "Stok";
		}
		else if (data instanceof Alokasi)
		{
			title += "Alokasi";
		}
		else if (data instanceof Pengiriman)
		{
			title += "Pengiriman";
		}
		this.labelTitle = new JLabel(title);
		
		//Properties
		this.panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.labelTitle.setFont(FontConstants.GENERAL_FONT_BOLD);
		
		//Add to panel
		this.panelTop.add(this.labelTitle);
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		
		//Properties
		this.panelCenter.setLayout(new GridBagLayout());
		
		//Add to panel
		if (data instanceof Stock)
		{
			this.initPanelStock();
		}
		else if(data instanceof Alokasi)
		{
			this.initPanelAlokasi();
		}
		else if (data instanceof Pengiriman)
		{
			this.initPanelPengiriman();
		}
	}
	private void init()
	{
		//Initialization
		this.initPanelCenter();
		this.initPanelTop();
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.panelTop, BorderLayout.NORTH);
		this.add(this.panelCenter, BorderLayout.CENTER);
	}
	//Selection
	private void initPanelStock()
	{
		//Initialization
		Stock stock = (Stock)this.data;
		BoldedLabel labItem = new BoldedLabel("Nama Item", SwingConstants.RIGHT);
		JLabel item = new JLabel(stock.getItemName(), SwingConstants.LEFT);
		BoldedLabel labItemCount = new BoldedLabel("Jumlah Item", SwingConstants.RIGHT);
		JLabel itemCount = new JLabel(Long.toString(stock.getItemCount()), SwingConstants.LEFT);
		BoldedLabel labItemDesc = new BoldedLabel("Keterangan Item", SwingConstants.RIGHT);
		JLabel itemDesc = new JLabel(stock.getItemDescription(), SwingConstants.LEFT);
		BoldedLabel labDateModif = new BoldedLabel("Tanggal Modifikasi Terakhir", SwingConstants.RIGHT);
		JLabel dateModif = new JLabel(Methods.getDateAsString(stock.getDateLastModified()), SwingConstants.LEFT);
		BoldedLabel labDateInput = new BoldedLabel("Tanggal Input", SwingConstants.RIGHT);
		JLabel dateInput = new JLabel(Methods.getDateAsString(stock.getDateFirstEntered()), SwingConstants.LEFT);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = InsetsConstants.GENERAL;
		c.fill = GridBagConstraints.BOTH;
		this.panelCenter.add(labItem, c);					//Item Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(item, c);						//Item
		Gbm.newGridLine(c);
		this.panelCenter.add(labItemCount, c);				//Item count label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(itemCount, c);					//Item count
		Gbm.newGridLine(c);
		this.panelCenter.add(labItemDesc, c);				//Item desc label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(itemDesc, c);					//Item desc
		Gbm.newGridLine(c);
		this.panelCenter.add(new JLabel(""), c);			//Divider
		Gbm.newGridLine(c);
		this.panelCenter.add(labDateModif, c);				//Date last modify label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(dateModif, c);					//Date last modify
		Gbm.newGridLine(c);
		this.panelCenter.add(labDateInput, c);				//Date input label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(dateInput, c);					//Date input
	}
	private void initPanelAlokasi()
	{
		Alokasi alokasi = (Alokasi)this.data;
		Site site = Methods.getSiteByID(alokasi.getSite());
		BoldedLabel labItem = new BoldedLabel("Item:", SwingConstants.RIGHT),
					labProgram = new BoldedLabel("Program:", SwingConstants.RIGHT),
					labSiteID = new BoldedLabel("ID Site:", SwingConstants.RIGHT),
					labSiteName = new BoldedLabel("Nama Site:", SwingConstants.RIGHT),
					labSiteArea = new BoldedLabel("Area:", SwingConstants.RIGHT),
					labItemCount = new BoldedLabel("Jumlah:", SwingConstants.RIGHT),
					labItemDesc = new BoldedLabel("Keterangan:", SwingConstants.RIGHT),
					labPIC = new BoldedLabel("PIC:", SwingConstants.RIGHT),
					labFileName = new BoldedLabel("Nama File:", SwingConstants.RIGHT),
					labUploadDate = new BoldedLabel("Tanggal Upload:", SwingConstants.RIGHT);
				
		JLabel item = new JLabel(alokasi.getItem(), SwingConstants.LEFT),
				program = new JLabel(alokasi.getProgram(), SwingConstants.LEFT),
				siteID = new JLabel(site.getID(), SwingConstants.LEFT),
				siteName = new JLabel(site.getName(), SwingConstants.LEFT),
				siteArea = new JLabel(site.getArea(), SwingConstants.LEFT),
				itemCount = new JLabel(Long.toString(alokasi.getAmount()), SwingConstants.LEFT),
				itemDesc = new JLabel(alokasi.getItemInfo(), SwingConstants.LEFT),
				pic = new JLabel(alokasi.getPIC(), SwingConstants.LEFT),
				fileName = new JLabel(alokasi.getFileName(), SwingConstants.LEFT),
				uploadDate = new JLabel(Methods.getDateAsString(alokasi.getUploadDate()), SwingConstants.LEFT);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.fill = GridBagConstraints.BOTH;
		c.insets = InsetsConstants.GENERAL;
		this.panelCenter.add(labProgram, c);					//Program label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(program, c);						//Program
		Gbm.newGridLine(c);
		this.panelCenter.add(labSiteID, c);						//Site ID Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(siteID, c);						//Site ID
		Gbm.newGridLine(c);
		this.panelCenter.add(labSiteName, c);					//Site name label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(siteName, c);						//Site Name
		Gbm.newGridLine(c);
		this.panelCenter.add(labSiteArea, c);					//Site Area Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(siteArea, c);						//Site area
		Gbm.newGridLine(c);
		this.panelCenter.add(new JLabel(), c);
		Gbm.newGridLine(c);
		this.panelCenter.add(labItem, c);						//Item label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(item, c);							//Item
		Gbm.newGridLine(c);
		this.panelCenter.add(labItemCount, c);					//Item count label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(itemCount, c);						//Item Count
		Gbm.newGridLine(c);
		this.panelCenter.add(labItemDesc, c);					//Item Description label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(itemDesc, c);						//Item Description
		Gbm.newGridLine(c);
		this.panelCenter.add(new JLabel(), c);
		Gbm.newGridLine(c);
		this.panelCenter.add(labPIC, c);						//PIC Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(pic, c);							//PIC
		Gbm.newGridLine(c);
		this.panelCenter.add(new JLabel(), c);					
		Gbm.newGridLine(c);
		this.panelCenter.add(labFileName, c);					//File Name Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(fileName, c);						//File Name
		Gbm.newGridLine(c);
		this.panelCenter.add(labUploadDate, c);					//Upload Date Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(uploadDate, c);					//Upload Date
	}
	private void initPanelPengiriman()
	{
		Pengiriman pengiriman = (Pengiriman)data;
		Site site = Methods.getSiteByID(pengiriman.getSite());
		
		//Initialization
		BoldedLabel labDateSent = new BoldedLabel("Tanggal:", SwingConstants.RIGHT),	
					labProgram = new BoldedLabel("Nama Program:", SwingConstants.RIGHT),
					labSiteID = new BoldedLabel("ID Site:", SwingConstants.RIGHT),
					labSiteName = new BoldedLabel("Nama Site:", SwingConstants.RIGHT),	
					labSiteArea = new BoldedLabel("Area:", SwingConstants.RIGHT),	
					labPengirim = new BoldedLabel("Pengirim:", SwingConstants.RIGHT),
					labPenerima = new BoldedLabel("Penerima:", SwingConstants.RIGHT),	
					labInfoFromSite = new BoldedLabel("Info Site:", SwingConstants.RIGHT),	
					labItem = new BoldedLabel("Item:", SwingConstants.RIGHT),
					labItemCount = new BoldedLabel("Jumlah Item:", SwingConstants.RIGHT),
					labFileName = new BoldedLabel("Nama File:", SwingConstants.RIGHT),
					labDateUpload = new BoldedLabel("Tanggal Upload:", SwingConstants.RIGHT);
				
		JLabel dateSent = new JLabel(Methods.getDateAsString(pengiriman.getDateSent()), SwingConstants.LEFT),
				program = new JLabel(pengiriman.getProgram(), SwingConstants.LEFT),
				siteID = new JLabel(site.getID(), SwingConstants.LEFT),
				siteName = new JLabel(site.getName(), SwingConstants.LEFT),
				siteArea = new JLabel(site.getArea(), SwingConstants.LEFT),
				pengirim = new JLabel(pengiriman.getSender(), SwingConstants.LEFT),
				penerima = new JLabel(pengiriman.getReceiver(), SwingConstants.LEFT),
				inforFromSite = new JLabel(pengiriman.getInfoFromSite(), SwingConstants.LEFT),	
				item = new JLabel(pengiriman.getItemName(), SwingConstants.LEFT),
				itemCount = new JLabel(Long.toString(pengiriman.getAmount()), SwingConstants.LEFT),
				fileName = new JLabel(pengiriman.getFileName(), SwingConstants.LEFT),
				dateUpload = new JLabel(Methods.getDateAsString(pengiriman.getDateUpload()), SwingConstants.LEFT);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.fill = GridBagConstraints.BOTH;
		c.insets = InsetsConstants.GENERAL;
		this.panelCenter.add(labDateSent, c);			//Date Sent Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(dateSent, c);				//Date Sent
		Gbm.newGridLine(c);
		this.panelCenter.add(labProgram, c);			//Program Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(program, c);				//Program
		Gbm.newGridLine(c);
		this.panelCenter.add(labSiteID, c);				//Site ID Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(siteID, c);				//Site ID
		Gbm.newGridLine(c);
		this.panelCenter.add(labSiteName, c);			//Site Name Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(siteName, c);				//Site Name
		Gbm.newGridLine(c);
		this.panelCenter.add(labSiteArea, c);			//Site Area Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(siteArea, c);				//Site Area
		Gbm.newGridLine(c);
		this.panelCenter.add(labPengirim, c);			//Pengirim Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(pengirim, c);				//Pengirim
		Gbm.newGridLine(c);
		this.panelCenter.add(labPenerima, c);			//Penerima Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(penerima, c);				//Penerima
		Gbm.newGridLine(c);
		this.panelCenter.add(labInfoFromSite, c);		//Info From Site Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(inforFromSite, c);			//Info From Site
		Gbm.newGridLine(c);
		this.panelCenter.add(new JLabel(), c);		
		Gbm.newGridLine(c);
		this.panelCenter.add(labItem, c);				//Item Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(item, c);					//Item
		Gbm.newGridLine(c);
		this.panelCenter.add(labItemCount, c);			//Item Count Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(itemCount, c);				//item Count
		Gbm.newGridLine(c);
		this.panelCenter.add(new JPanel(), c);
		Gbm.newGridLine(c);
		this.panelCenter.add(labFileName, c);			//File Name labels
		Gbm.nextGridColumn(c);
		this.panelCenter.add(fileName, c);				//File Name
		Gbm.newGridLine(c);
		this.panelCenter.add(labDateUpload, c);			//Date Upload Label
		Gbm.nextGridColumn(c);
		this.panelCenter.add(dateUpload, c);			//Date Upload
	}

}