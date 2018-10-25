package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
		Stock stock = (Stock)this.data;
		JLabel labItem = new JLabel("Nama Item");
		JLabel item = new JLabel(stock.getItemName());
		JLabel labItemCount = new JLabel("Jumlah Item");
		JLabel itemCount = new JLabel(Long.toString(stock.getItemCount()));
		JLabel labItemDesc = new JLabel("Keterangan Item");
		JLabel itemDesc = new JLabel(stock.getItemDescription());
		JLabel labDateModif = new JLabel("Tanggal Modifikasi Terakhir");
		JLabel dateModif = new JLabel(Methods.getDateAsString(stock.getDateLastModified()));
		JLabel labDateInput = new JLabel("Tanggal Input");
		JLabel dateInput = new JLabel(Methods.getDateAsString(stock.getDateFirstEntered()));
		
		Gbm.goToOrigin(c);
		c.insets = InsetsConstants.GENERAL;
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
		JLabel labItem = new JLabel("Item"),
				item = new JLabel(alokasi.getItem()),
				labProgram = new JLabel("Program"),
				program = new JLabel(alokasi.getProgram()),
				labSiteID = new JLabel("ID Site"),
				siteID = new JLabel(site.getID()),
				labSiteName = new JLabel("Nama Site"),
				siteName = new JLabel(site.getName()),
				labSiteArea = new JLabel("Area"),
				siteArea = new JLabel(site.getArea()),
				labItemCount = new JLabel("Jumlah"),
				itemCount = new JLabel(Long.toString(alokasi.getAmount())),
				labItemDesc = new JLabel("Keterangan"),
				itemDesc = new JLabel(alokasi.getItemInfo()),
				labPIC = new JLabel("PIC"),
				pic = new JLabel(alokasi.getPIC()),
				labFileName = new JLabel("Nama File"),
				fileName = new JLabel(alokasi.getFileName()),
				labUploadDate = new JLabel("Tanggal Upload"),
				uploadDate = new JLabel(Methods.getDateAsString(alokasi.getUploadDate()));
		
		//Add to panel
		Gbm.goToOrigin(c);
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
		JLabel labDateSent = new JLabel("Tanggal"),
				dateSent = new JLabel(Methods.getDateAsString(pengiriman.getDateSent())),
				labProgram = new JLabel("Nama Program"),
				program = new JLabel(pengiriman.getProgram()),
				labSiteID = new JLabel("ID Site"),
				siteID = new JLabel(site.getID()),
				labSiteName = new JLabel("Nama Site"),
				siteName = new JLabel(site.getName()),
				labSiteArea = new JLabel("Area"),
				siteArea = new JLabel(site.getArea()),
				labPengirim = new JLabel("Pengirim"),
				pengirim = new JLabel(pengiriman.getSender()),
				labPenerima = new JLabel("Penerima"),
				penerima = new JLabel(pengiriman.getReceiver()),
				labInfoFromSite = new JLabel("Info Site"),
				inforFromSite = new JLabel(pengiriman.getInfoFromSite()),
				labItem = new JLabel("Item"),
				item = new JLabel(pengiriman.getItemName()),
				labItemCount = new JLabel("Jumlah Item"),
				itemCount = new JLabel(Long.toString(pengiriman.getAmount())),
				labFileName = new JLabel("Nama File"),
				fileName = new JLabel(pengiriman.getFileName()),
				labDateUpload = new JLabel("Tanggal Upload"),
				dateUpload = new JLabel(Methods.getDateAsString(pengiriman.getDateUpload()));
		
		//Add to panel
		Gbm.goToOrigin(c);
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