
package com.exlibris.alma_me.test3;

import java.io.InputStream;
import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 *
 * @author amirc
 */
public class CSVreader implements CountData
{
    /**
     *
     * @param file
     * @param migServer
     * @return
     * @throws Exception
     */
    public void countFiles(String migServer, String migEnv, String areaChecked, String file,String type) throws Exception
    {
    	String [] output = null;
    	String [] finalrow = null;
        String host = migServer;
        String command1 = null;


        if (type.contentEquals("csv"))
        {
	        if (Pattern.compile(Pattern.quote("item"), Pattern.CASE_INSENSITIVE).matcher(areaChecked).find())
	        {
	        	command1 = "dinst " + migEnv + "  ; go input ; cd items/ ; wc -l " + file;

	        }

	        else {
	        	command1 = "dinst " + migEnv + "  ; go input ; wc -l " + file;
	        }

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

	                finalrow = output[output.length -1].split("\\s+");
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

//	        for (int i = 0; i < output.length; i++)
//	        {
//	        	System.out.println(i + ": " + output[i]);
//
//	        }

	        if (finalrow[0].isEmpty())
	        {
	        	System.out.println(finalrow[1]);
	        }
	        else
	        {
	        	System.out.println(finalrow[0]);
	        }

	    }


	//    public static void main(String[] args) throws Exception
	//    {
	//        // TODO code application logic here
	//       if (args.length > 0)
	//    	   countFiles(args[0], args[1], args[2], args[3]);
	//    }

    }

}
