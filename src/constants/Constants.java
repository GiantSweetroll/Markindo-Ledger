package constants;

import java.awt.Color;

public class Constants
{
	public static final String STOCK_FOLDER_PATH = "./data/stock/";
	public static final String STOCK_FILE_EXTENSION = ".stck";
	public static final String ALOKASI_FOLDER_PATH = "./data/alokasi/";
	public static final String ALOKASI_FILE_EXTENSION = ".alks";
	public static final String PROGRAM_FOLDER_PATH = "./data/program/";
	public static final String PROGRAM_FILE_EXTENSION = ".prgm";
	public static final String PENGIRIMAN_FOLDER_PATH = "./data/pengiriman/";
	public static final String PENGIRIMAN_FILE_EXTENSION = ".send";
	public static final String SITE_FOLDER_PATH = "./data/site/";
	public static final String SITE_FILE_EXTENSION = ".site";
	public static final String PIC_FOLDER_PATH = "./data/pic/";
	public static final String PIC_FILE_EXTENSION = ".pic";
	
	//Color
//	public static final Color HIGHLIGHT_COLOR = new Color(0, 120, 215);
	public static final Color SECTION_BORDER_COLOR = new Color(160,160, 160);
	public static final Color BUTTON_COLOR = new Color(70, 119, 180);
//	public static final Color BUTTON_COLOR = new Color(23, 74, 211);
	public static final Color PANEL_COLOR = new Color(41, 44, 49);
	public static final Color PANEL_COLOR_2 = new Color(36, 41, 48);
	public static final Color MENU_BAR_COLOR = new Color(56, 57, 62);
	public static final Color TABLE_HEADER_COLOR = new Color(179, 131, 0);
	public static final Color DISABLED_COLOR = Color.GRAY;
	public static final Color TEXT_FIELD_ENABLED_COLOR = Constants.MENU_BAR_COLOR;
	public static final Color TEXT_FIELD_DISABLED_COLOR = Constants.DISABLED_COLOR;
	
	
	public static final String ACTION_CELL_NAME = "Aksi";
	
	public static final String[] STOCK_TABLE_HEADERS = {"Item", "Jumlah", "Tanggal Input", "Tanggal Modifikasi Terakhir"};
	public static final String[] ALOKASI_TABLE_HEADERS = {"Program", "Site", "Item", "Sisa Alokasi"};
	public static final String[] SITE_TABLE_HEADERS = {"Nama Site", "Area", "Info"};
	public static final String[] PIC_TABLE_HEADERS = {"PIC"};
	public static final String[] PROGRAM_TABLE_HEADERS = {"Program"};
	public static final String[] PENGIRIMAN_TABLE_HEADERS = {"Tanggal", "Program", "Site", "Item", "Jumlah", "Pengirim"};

	public static final String[] SENDERS = {"Agus", "Ano", "Deni", "Eko", "Gito", "Hans", "Jodi", "Rafiq", "Ujang", "Yasin"};
}