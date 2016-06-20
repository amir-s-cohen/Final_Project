package com.exlibris.alma_me.test3;


// interface for Count Date
public interface CountData
{
	String user="urm";
    String password="urm";

	public void countFiles(String migServer, String migEnv, String areaChecked, String file,String type) throws Exception;

}
