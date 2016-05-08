package com.exlibris.alma_me.test3;

public class AlmaCountQueries
{

	public String countNumOfBibs(String instID)
	{
		String qu = "SELECT COUNT (*) FROM mms_record WHERE objecttype='BIB_MMS' AND institutionid=" + instID + " AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}
	public String countNumOfHoldings(String instID)
	{
		String qu = "SELECT COUNT (*) FROM mms_record WHERE objecttype='HOLDING' AND institutionid=" + instID + "  AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}
	public String countNumOfItems(String instID)
	{
		String qu = "SELECT COUNT (*) FROM hdemetadata WHERE objecttype='ITEM' AND institutionid=" + instID + " AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}
	public String countNumOfloans(String instID)
	{
		String qu = "SELECT COUNT (*) FROM item_loan WHERE institutionid=" + instID + " AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}



}
