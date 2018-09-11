package constants;

import java.awt.Insets;

public class InsetsConstants
{
	public static final int GENERAL_INSETS_SIZE = 5;
	
	public static final Insets GENERAL = new Insets (GENERAL_INSETS_SIZE, GENERAL_INSETS_SIZE, GENERAL_INSETS_SIZE, GENERAL_INSETS_SIZE);
	public static final Insets TITLE = new Insets (GENERAL.top*4, GENERAL.left*4, GENERAL.bottom*4, GENERAL.right*4);
}
