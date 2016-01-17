package com.exlibris.alma_me.test3;

public interface CountData
{
	String user="urm";
    String password="urm";

	public void countFiles(String migServer, String migEnv, String areaChecked, String file,String type) throws Exception;

}
