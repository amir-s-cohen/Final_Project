package com.exlibris.alma_me.test3;

public class DataCountFactory
{
	public CountData getCsvCount()
	{

		return new CSVreader();

	}
	public CountData getXmlCount()
	{
		return new XMLreader();
	}

	public CountData getAlmaData()
	{
		return new AlmaDataReader();
	}
}
