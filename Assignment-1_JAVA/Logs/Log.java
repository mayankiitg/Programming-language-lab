/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

package Logger;

import java.lang.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class Log
{
    private static String logFileName;
    
    private static PrintWriter writer;
    private static String CurrentTimestamp()
    {
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        String formattedDate = "[" + sdf.format(date) + "]";
        return formattedDate;
        
    }
    
    public synchronized static void Initialize() throws FileNotFoundException
    {
        File file = new File("Logs/log.txt");
        file.getParentFile().mkdirs();
        Log.writer = new PrintWriter(file);
    }
    public synchronized static void LogError(String error)
    {
        String Location = "[File: " + 
                            Thread.currentThread().getStackTrace()[2].getFileName() +
                            " Line: " +
                            Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "]";
        Log.writer.println(Location + " " + CurrentTimestamp() + " [Thread " + Thread.currentThread().getId() + "]" + " [Error] " + error);
        Log.writer.flush();
    }
    
    public synchronized static void LogWarning(String error)
    {
        String Location = "[File: " + 
                            Thread.currentThread().getStackTrace()[2].getFileName() +
                            " Line: " +
                            Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "]";
        Log.writer.println(Location + " " + CurrentTimestamp() + " [Thread " + Thread.currentThread().getId() + "]" + " [Warning] " + error);
        Log.writer.flush();
    }
    
    public synchronized static void LogInfo(String error)
    {
        String Location = "[File: " + 
                            Thread.currentThread().getStackTrace()[2].getFileName() +
                            " Line: " +
                            Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "]";
        Log.writer.println(Location + " " + CurrentTimestamp() + " [Thread " + Thread.currentThread().getId() + "]" + " [Info] " + error);
        Log.writer.flush();
    }
}