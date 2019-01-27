package gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import constants.URLConstants;
import giantsweetroll.ImageManager;

public class BannerPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283022622758026099L;
	
	private JLabel labBanner;
	
	public BannerPanel()
	{
		super(new FlowLayout(FlowLayout.LEFT));
		//initialization
		this.labBanner = new JLabel(ImageManager.getImageIcon(URLConstants.MARKINDO_LOGO_BANNER));
		
		//Properties
//		this.setBackground(new Color(57, 56, 54));
		this.setBackground(Constants.MENU_BAR_COLOR);
		
		//Add to panel
		this.add(labBanner);
	}
}
