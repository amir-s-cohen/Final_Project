package com.exlibris.alma_me.test3;

public class AlmaCountQueries
{

	// Queries to count in Alma

	// Bibs
	public String countNumOfBibs(String instID)
	{
		String qu = "SELECT COUNT (*) FROM mms_record WHERE objecttype='BIB_MMS' AND institutionid=" + instID + " AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}
	//Holdings
	public String countNumOfHoldings(String instID)
	{
		String qu = "SELECT COUNT (*) FROM mms_record WHERE objecttype='HOLDING' AND institutionid=" + instID + "  AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}
	// Items
	public String countNumOfItems(String instID)
	{
		String qu = "SELECT COUNT (*) FROM hdemetadata WHERE objecttype='ITEM' AND institutionid=" + instID + " AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}
	//Loans
	public String countNumOfloans(String instID)
	{
		String qu = "SELECT COUNT (*) FROM item_loan WHERE institutionid=" + instID + " AND ORIGINATING_SYSTEM_ID IS NOT NULL" ;

		return qu;
	}



}
