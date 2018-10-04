package methods;

import giantsweetroll.numbers.GNumbers;

public class IDGenerator
{
	public static final String SITE = "S",
						 		PENGIRIMAN = "P";
	public static final int MAX_DIGITS = 8;
	
	private static String generateCode()
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<IDGenerator.MAX_DIGITS; i++)
		{
			sb.append(Integer.toString(GNumbers.randomize(0, 9)));
		}
		
		return sb.toString();
	}

	public static String generate(String prefix)
	{
		return prefix + IDGenerator.generateCode();
	}
	public static boolean hasSameID(String id1, String id2)
	{
		return id1.equalsIgnoreCase(id2);
	}
}
