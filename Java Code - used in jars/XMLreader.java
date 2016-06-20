package com.exlibris.alma_me.test3;

import java.io.InputStream;
import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class XMLreader implements CountData
{

	@Override
	public void countFiles(String migServer, String migEnv, String areaChecked,	String file, String type) throws Exception
	{
		String [] output = null;
    	String [] finalrow = null;
        String host = migServer;
//        String user="urm";
//        String password="urm";
        String command1 = null;

// implementation of CountFiles

        if (type.contentEquals("xml"))
        {

        	if ((Pattern.compile(Pattern.quote("VENDOR"), Pattern.CASE_INSENSITIVE).matcher(areaChecked).find()) || (Pattern.compile(Pattern.quote("LOAN"), Pattern.CASE_INSENSITIVE).matcher(areaChecked).find()))
        	{
        		command1 = "dinst " + migEnv + "  ; go output ; cd " + areaChecked + "; ls | wc -l";
        	}
        	if ((Pattern.compile(Pattern.quote("FUND"), Pattern.CASE_INSENSITIVE).matcher(areaChecked).find()))
        	{
        		command1 = "dinst " + migEnv + "  ; go output ; cd " + areaChecked + "; grep code *xml | wc -l";
        	}
        	if ((Pattern.compile(Pattern.quote("P2E"), Pattern.CASE_INSENSITIVE).matcher(areaChecked).find()))
        	{
        		command1 = "dinst " + migEnv + "  ; go output ; cd " + areaChecked + "; wc -l p2e.csv";
        	}
        	else
        	{
        		command1 = "dinst " + migEnv + "  ; go output ; cd " + areaChecked + "; find . -name '*xml' | wc -l";
        	}
//        }

        try
        {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
//            System.out.println("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true)
            {
              while (in.available() > 0)
              {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;


//                System.out.print(new String(tmp, 0, i));
                //output = (new String(tmp, 0, i)).split("\\r?\\n");
                output = (new String(tmp, 0, i)).split("\\r?\\n");
                finalrow = output[output.length -1].split("[0-9] [a-zA-Z]");
              }
              if(channel.isClosed()){
//                System.out.println("exit-status: "+channel.getExitStatus());
                break;
              }
              try
              {
            	  Thread.sleep(1000);
            	  }
              catch
              (Exception ee)
              {}
            }
            channel.disconnect();
            session.disconnect();
//            System.out.println("DONE");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        System.out.println(finalrow[0]);

        }

	}
}

