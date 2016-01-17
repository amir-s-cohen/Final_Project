package com.exlibris.alma_me.test3;

import java.io.IOException;

import com.exlibris.alma_me.model.*;
import com.exlibris.alma_me.util.AlmaMeStringUtil;
import com.exlibris.alma_me.util.ExcelMap;
import com.exlibris.alma_me.util.FiscalPeriodTable;
import com.exlibris.alma_me.util.Questionnaire;


import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import jxl.read.biff.BiffException;
import com.exlibris.alma_me.util.*;
//
public class compareDeliverdListWithProvidedData
{

	static String [] areaName = new String[25];


	//bibs, holdings, items  patrons  loans  requests  orders vendors funds p2e suppressed
	static String [] areaNameMap = new String[15];

	private enum MigrationArea
	{
		bibs, SuppresedBibs, holdings, items, patrons, loans, requests, orders, vendors, funds, invoices, p2e;
	}




	public  void readExcel(String deliverdFileList) throws BiffException, IOException
	{
		File myFile = new File(deliverdFileList);
		FileInputStream fis = new FileInputStream(myFile);
		// Finds the workbook instance for XLSX file

		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		Iterator<Row> rowIterator = mySheet.iterator();

		while (rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext())
			{

				Cell cell = cellIterator.next();
				switch (cell.getCellType())
				{
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t");
					break; default :
				}
			}
			System.out.println("");
		}


	}

	public static String getDeliverdFilesListValues(String deliverdFileList, String areaDesired)
	{

		try {
			getValueToMap(deliverdFileList);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		String numOfbibs;
		String numOfSuppresedBibs;
		String numOfItems;
		String numOfHoldings;
		String numOfPatrons;
		String numOfLoans;
		String numOfRequests;
		String numOfOrders;
		String numOfVendors;
		String numOfFunds;
		String numOfP2Erecords;



		if (deliverdFileList == null || deliverdFileList.isEmpty())
		{
			System.out.println("No Delivered Files List was provided");
			//				return "";
		}

		//bibs, holdings, items  patrons  loans  requests  orders vendors funds p2e suppressed

		numOfbibs = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[0], 0, 5);
		if (numOfbibs.equals("ALMAME_VAL_NULL") || numOfbibs.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfbibs = "N/A";
		}
		numOfSuppresedBibs = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[10], 0, 5);
		if (numOfSuppresedBibs.equals("ALMAME_VAL_NULL") || numOfSuppresedBibs.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfSuppresedBibs = "N/A";
		}
		numOfItems = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[2], 0, 5);
		if (numOfItems.equals("ALMAME_VAL_NULL") || numOfItems.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfItems = "N/A";
		}
		numOfHoldings = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[1], 0, 5);
		if (numOfHoldings.equals("ALMAME_VAL_NULL") || numOfHoldings.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfHoldings = "N/A";
		}
		numOfPatrons = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[3], 0, 5);
		if (numOfPatrons.equals("ALMAME_VAL_NULL") || numOfPatrons.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfPatrons = "N/A";
		}
		numOfLoans = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[4], 0, 5);
		if (numOfLoans.equals("ALMAME_VAL_NULL") || numOfLoans.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfLoans = "N/A";
		}
		numOfRequests = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[5], 0, 5);
		if (numOfRequests.equals("ALMAME_VAL_NULL") || numOfRequests.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfRequests = "N/A";
		}
		numOfOrders = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[6], 0, 5);
		if (numOfOrders.equals("ALMAME_VAL_NULL") || numOfOrders.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfOrders = "N/A";
		}
		numOfVendors = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[7], 0, 5);
		if (numOfVendors.equals("ALMAME_VAL_NULL") || numOfVendors.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfVendors = "N/A";
		}
		numOfP2Erecords = ExcelMap.getValueMF(deliverdFileList, "Overview",areaNameMap[9], 0, 5);
		if (numOfP2Erecords.equals("ALMAME_VAL_NULL") || numOfP2Erecords.equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfP2Erecords = "N/A";
		}
		numOfFunds = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[8], 0, 5);
		if (numOfFunds.equals("ALMAME_VAL_NULL") || numOfFunds .equals("ALMAME_VAL_NOT_FOUND"))
		{
			numOfFunds = "N/A";
		}



		//			System.out.println("Number of Provided bibs:           " + numOfbibs);
		//			System.out.println("Number of Provided Suppresed bibs: " + numOfSuppresedBibs);
		//			System.out.println("Number of Provided numOfHoldings:  " + numOfHoldings);
		//			System.out.println("Number of Provided Items:          " + numOfItems);
		//			System.out.println("Number of Provided Patrons:        " + numOfPatrons);
		//			System.out.println("Number of Provided Loans:          " + numOfLoans);
		//			System.out.println("Number of Provided Requests:       " + numOfRequests);
		//			System.out.println("Number of Provided Orders:         " + numOfOrders);
		//			System.out.println("Number of Provided Funds:          " + numOfFunds);
		//			System.out.println("Number of Provided P2E records:    " + numOfP2Erecords);

		MigrationArea area = MigrationArea.valueOf(areaDesired);

		switch (area)
		{
		case bibs:
			return numOfbibs;

		case SuppresedBibs:
			return numOfSuppresedBibs;
		case holdings:
			return numOfHoldings;
		case items:
			return numOfItems;
		case patrons:
			return numOfPatrons;
		case loans:
			return numOfLoans;
		case requests:
			return numOfRequests;
		case orders:
			return numOfOrders;
		case vendors:
			return numOfVendors;
		case funds:
			return numOfFunds;
		case p2e:
			return numOfP2Erecords;

		}
		return "Please check that you eneter a correct Migration Area";
	}

	public static String getFileNames(String deliverdFileList, String areaDesired)
	{
		try {
			getValueToMap(deliverdFileList);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String nameOfbibs;
		String nameOfSuppresedBibs;
		String nameOfItems;
		String nameOfHoldings;
		String nameOfPatrons;
		String nameOfLoans;
		String nameOfRequests;
		String nameOfOrders;
		String nameOfVendors;
		String nameOfFunds;
		String nameOfP2Erecords;

		if (deliverdFileList == null || deliverdFileList.isEmpty())
		{
			System.out.println("No Delivered Files List was provided");
			//				return "";
		}
		//bibs, holdings, items  patrons  loans  requests  orders vendors funds p2e suppressed

		nameOfbibs = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[0], 0, 2);
		if (nameOfbibs.equals("ALMAME_VAL_NULL") || nameOfbibs.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfbibs = "N/A";
		}
		nameOfSuppresedBibs = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[10], 0, 2);
		if (nameOfSuppresedBibs.equals("ALMAME_VAL_NULL") || nameOfSuppresedBibs.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfSuppresedBibs = "N/A";
		}
		nameOfItems = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[2], 0, 2);
		if (nameOfItems.equals("ALMAME_VAL_NULL") || nameOfItems.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfItems = "N/A";
		}
		nameOfHoldings = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[1], 0, 2);
		if (nameOfHoldings.equals("ALMAME_VAL_NULL") || nameOfHoldings.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfHoldings = "N/A";
		}
		nameOfPatrons = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[3], 0, 2);
		if (nameOfPatrons.equals("ALMAME_VAL_NULL") || nameOfPatrons.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfPatrons = "N/A";
		}
		nameOfLoans = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[4], 0, 2);
		if (nameOfLoans.equals("ALMAME_VAL_NULL") || nameOfLoans.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfLoans = "N/A";
		}
		nameOfRequests = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[5], 0, 2);
		if (nameOfRequests.equals("ALMAME_VAL_NULL") || nameOfRequests.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfRequests = "N/A";
		}
		nameOfOrders = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[6], 0, 2);
		if (nameOfOrders.equals("ALMAME_VAL_NULL") || nameOfOrders.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfOrders = "N/A";
		}
		nameOfVendors = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[7], 0, 2);
		if (nameOfVendors.equals("ALMAME_VAL_NULL") || nameOfVendors.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfVendors = "N/A";
		}
		nameOfP2Erecords = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[9], 0, 2);
		if (nameOfP2Erecords.equals("ALMAME_VAL_NULL") || nameOfP2Erecords.equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfP2Erecords = "N/A";
		}
		nameOfFunds = ExcelMap.getValueMF(deliverdFileList, "Overview", areaNameMap[8], 0, 2);
		if (nameOfFunds.equals("ALMAME_VAL_NULL") || nameOfFunds .equals("ALMAME_VAL_NOT_FOUND"))
		{
			nameOfFunds = "N/A";
		}

		MigrationArea area = MigrationArea.valueOf(areaDesired);

		switch (area)
		{
		case bibs:
			return nameOfbibs;
		case SuppresedBibs:
			return nameOfSuppresedBibs;
		case holdings:
			return nameOfHoldings;
		case items:
			return nameOfItems;
		case patrons:
			return nameOfPatrons;
		case loans:
			return nameOfLoans;
		case requests:
			return nameOfRequests;
		case orders:
			return nameOfOrders;
		case vendors:
			return nameOfVendors;
		case funds:
			return nameOfFunds;
		case p2e:
			return nameOfP2Erecords;

		}
		return "Please check that you eneter a correct Migration Area";


	}
	public static void getValueToMap(String file) throws BiffException, IOException
	{
		if (file == null || file.isEmpty())
		{
			System.out.println("No deliverted file");
			return;
		}
		DataFormatter df = new DataFormatter();
		String stringCellValue = null;

		File myFile = new File(file);
		FileInputStream fis = new FileInputStream(myFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		Iterator<Row> rowIterator = mySheet.iterator();
		int i = 0;

		while (rowIterator.hasNext() && i < areaName.length)
		{

			Row row = rowIterator.next();

			//			            System.out.println(row.getCell(0));

			stringCellValue = df.formatCellValue(row.getCell(0));
			areaName[i] = stringCellValue;
			i++;

		}
		//			        for (String s:areaName)
		//			        {
		//			        	System.out.println(s);
		//			        }


		for (int j = 0; j < areaName.length; j++)
		{
			/*
			 * bibs, holdings, items  patrons  loans  requests  orders vendors funds p2e suppressed
			 *
			 */
			//System.out.println("THis is J: " + j);
			if (areaName[j] == null || areaName[j].isEmpty())
			{
				continue;
			}
			if (Pattern.compile(Pattern.quote("Biblio"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find() )
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[0] = areaName[j];
			}
			if ((!Pattern.compile(Pattern.quote("check"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find() && Pattern.compile(Pattern.quote("Holdings"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find() && Pattern.compile(Pattern.quote("marc"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find()) || (!Pattern.compile(Pattern.quote("check"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find() && Pattern.compile(Pattern.quote("holding"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find() && Pattern.compile(Pattern.quote("mrc"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find()))
			{

				//System.out.println("This works: " + areaName[j]);
				areaNameMap[1] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("items"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[2] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("patron"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[3] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("loans"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[4] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("requests"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[5] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("order"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[6] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("vendors"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[7] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("fund"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[8] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("p2e"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[9] = areaName[j];
			}
			if (Pattern.compile(Pattern.quote("suppress"), Pattern.CASE_INSENSITIVE).matcher(areaName[j]).find())
			{
				//System.out.println("This works: " + areaName[j]);
				areaNameMap[10] = areaName[j];
			}
		}

	}



	public static void main(String [] args) throws BiffException, IOException
	{

		//			for (String s:args)
		//			{
		//				System.out.println(s);
		//			}

		if (args.length > 0 )
		{

			if (args.length == 3)
			{
				System.out.println(getFileNames(args[0], args[1]));
			}

			else if (args.length == 2)
			{
				System.out.println(getDeliverdFilesListValues(args[0], args[1]));
			}
			else

			{
				System.out.println("Expecting Delivered Files, area and optional name ");
			}

		}
		else
			System.out.println("Dude - Give me a parameter!!");
		//getDeliverdFilesListValues("C:/Users/amirc/Desktop/Amir Cohen - BackUp/Alma Migration/ALMA Migration/MIGRATION PROJECTS/EU/44WHELF_NLW/CUTOVER/NLW_VTLS Delivered Files List_Cutover_20151023.xlsx");

		//System.out.println(getValueToMap("C:/Users/amirc/Desktop/Amir Cohen - BackUp/Alma Migration/ALMA Migration/MIGRATION PROJECTS/EU/44WHELF_NLW/CUTOVER/NLW_VTLS Delivered Files List_Cutover_20151023.xlsx"));

	}



}

