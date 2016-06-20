package com.exlibris.alma_me.test3;

import java.io.InputStream;
import java.sql.*;

import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class AlmaDataReader implements CountData
{
    /**
     *
     * @param file
     * @param migServer
     * @return
     * @throws Exception
     */

	// Count records in Alma

	public void countFiles(String implServer, String instid, String areaChecked, String file,String type) throws Exception
    {

    	AlmaCountQueries q = new AlmaCountQueries();
    	String query;
    	if (areaChecked.contentEquals("BIB"))
    	{
    		query = q.countNumOfBibs(instid);
    	}
    	else if (areaChecked.contentEquals("HOL"))
    	{
    		query = q.countNumOfHoldings(instid);
    	}
    	else if (areaChecked.contentEquals("ITEM"))
    	{
    		query = q.countNumOfItems(instid);
    	}
    	else if (areaChecked.contentEquals("LOAN"))
    	{
    		query = q.countNumOfloans(instid);
    	}
    	else
    		return ;


    	Class.forName("oracle.jdbc.driver.OracleDriver");
        String serverName = implServer;
        String portNumber = "1521";
        String sid = "alma";
        String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
        String username = "v2u1_urm00";
        String password = "v2u1_urm00";
        Connection conn = DriverManager.getConnection(url, username, password);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        while (rs.next())
        {
        for (int i = 1; i <= columnsNumber; i++)
        {
        	if (i > 1) System.out.print(",  ");

            String columnValue = rs.getString(i);

//            System.out.print("colVal: " + columnValue + " rsmd.getColName: " + rsmd.getColumnName(i));
            System.out.print(columnValue);
        }
        	System.out.println("");
        }
    }





}
