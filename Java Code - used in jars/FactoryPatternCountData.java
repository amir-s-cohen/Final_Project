package com.exlibris.alma_me.test3;

public class FactoryPatternCountData
{

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		DataCountFactory datcountFac = new DataCountFactory();
		CountData ct1 = datcountFac.getCsvCount();
		CountData ct2 = datcountFac.getXmlCount();
		CountData ct3 = datcountFac.getAlmaData();

		if (args.length > 0 )
		{
			if (args[4].contentEquals("csv"))
			{

				ct1.countFiles(args[0], args[1], args[2], args[3], args[4]);
			}

			else if (args[4].contentEquals("xml"))
			{
				ct2.countFiles(args[0], args[1], args[2], args[3], args[4]);
			}
			else if (args[4].contentEquals("DB"))
			{
				ct3.countFiles(args[0], args[1], args[2], args[3], args[4]);
			}
		}
		else
			System.out.println("No available output");

	}







}

