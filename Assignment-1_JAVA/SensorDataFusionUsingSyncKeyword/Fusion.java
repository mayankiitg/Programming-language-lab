/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

import java.lang.*;
import java.util.*;
import java.math.*;
import Logger.*;

/**
 * Defines Methods of fusion process of sensor data.
*/ 
public class Fusion implements Runnable
{
    private Integer[] fusionDataArray;  
    
    private static final int averageThresold = 100;
    
    private static final int addThresold = 10000;
    
    private static final BigInteger multiplyThresold = new BigInteger("100000");
    
    public Fusion(Integer[] fusionDataArray)
    {
        this.fusionDataArray = fusionDataArray;
    }
    
    /**
     * calculate the average of data
     * check if it crosses the threshold
    */
    private void averageSensorData()
    {
        long ans = 0;
        for (int i = 0; i < this.fusionDataArray.length; i++)
        {
            ans += this.fusionDataArray[i];
        }
        
        ans /= this.fusionDataArray.length;
        if (ans > this.averageThresold)
        {
            System.out.println(Colors.ANSI_GREEN + "Avg Op: Result-" + ans + " Threshold-" + this.averageThresold + " Verdict: state detected from (ops)\n" + Colors.ANSI_RESET);
            Log.LogInfo("Fusion Op: " + Thread.currentThread().getId() + " Avg: " + ans + ". state detected from (ops)\n");
        }
        else
        {
            System.out.println(Colors.ANSI_RED + "Avg Op: Result-" + ans + " Threshold-" + this.averageThresold + " Verdict: state NOT detected from (ops)\n" + Colors.ANSI_RESET);
            Log.LogInfo("Fusion Op: " + Thread.currentThread().getId() + " Avg: " + ans + ". state not detected from (ops)\n");
        }
    }
    /**
     * calculate the sum of data
     * check if crosses the threshold
    */
    private void addSensorData()
    {
        long ans = 0;
        for (int i = 0; i < this.fusionDataArray.length; i++)
        {
            ans += this.fusionDataArray[i];
        }
        
        if (ans > this.addThresold)
        {
            
            System.out.println(Colors.ANSI_GREEN + "Add Op: Result-" + ans + " Threshold-" + this.averageThresold + " Verdict: state detected from (ops)\n" + Colors.ANSI_RESET);
            Log.LogInfo("Fusion Op: " + Thread.currentThread().getId() + " Add: " + ans + ". state detected from (ops)\n");
        }
        else
        {
            System.out.println(Colors.ANSI_RED + "Add Op: Result-" + ans + " Threshold-" + this.averageThresold + " Verdict: state NOT detected from (ops)\n" + Colors.ANSI_RESET);
            Log.LogInfo("Fusion Op: " + Thread.currentThread().getId() + " Add: " + ans + ". state not detected from (ops)\n");
        }
    }
    /**
     * calculate product of the data
     * and check if crosses threshold
    */
    private void multiplySensorData()
    {
        BigInteger prod = new BigInteger("1");
        for(int i=0; i<this.fusionDataArray.length; i++)
        {
            prod = prod.multiply(new BigInteger(Integer.toString(this.fusionDataArray[i])));
        }
        
        if (prod.compareTo(this.multiplyThresold) == 1)
        {
            System.out.println(Colors.ANSI_GREEN + "Multiply Op: Result-" + prod + " Threshold-" + this.averageThresold + " Verdict: state detected from (ops)\n" + Colors.ANSI_RESET);
            Log.LogInfo("Fusion Op: " + Thread.currentThread().getId() + " Multiply: " + prod + ". state detected from (ops)\n");
        }
        else
        {
            System.out.println(Colors.ANSI_RED + "Multiply Op: Result-" + prod + " Threshold-" + this.averageThresold + " Verdict: state NOT detected from (ops)\n" + Colors.ANSI_RESET);
            Log.LogInfo("Fusion Op: " + Thread.currentThread().getId() + " Multiply: " + prod + ". state not detected from (ops)\n");
        }
    }
    
    @Override
    public void run()
    {
        Log.LogInfo("Fusion OPeration, ID: " + Thread.currentThread().getId() + " Fusion Data: " + Arrays.toString(this.fusionDataArray));
        averageSensorData();
        addSensorData();
        multiplySensorData();
    }
}