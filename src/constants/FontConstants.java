package constants;

import java.awt.Font;

public class FontConstants
{
	public static final String FONT_NAME_GENERAL = "verdana";
	public static final int FONT_SIZE_GENERAL = 15;
	
	public static final Font GENERAL_FONT_BOLD = new Font(FontConstants.FONT_NAME_GENERAL, Font.BOLD, FontConstants.FONT_SIZE_GENERAL);
	public static final Font SECTION_HEADER = new Font(FontConstants.FONT_NAME_GENERAL, Font.BOLD, FontConstants.FONT_SIZE_GENERAL + 10);
}
