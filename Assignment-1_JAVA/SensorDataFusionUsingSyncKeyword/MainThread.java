/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

import java.lang.*;
import java.util.*;
import Logger.*;
import MergeSortUsingForkJoin.*;

public class MainThread
{
    // Keep running continuously polling sensor reading queues and perform fusion operations. 
    private static void Worker()
    {
        while (true)
        {
            try
            {
                // obtain the data from all the sensors
                Object[] a = CommonVar.synchronizedQueue.Pop();
                Integer[] sensorDataArray = Arrays.copyOf(a, a.length, Integer[].class);
                System.out.println("Data retrieved: " + Colors.ANSI_BLUE + Arrays.toString(sensorDataArray) + Colors.ANSI_RESET);
                // Sort retrieved data by ForkJoin MergeSort
                if(CommonVar.sortRequired == SortOrNot.SortYes)
                {
                    MergeSort.Sort(sensorDataArray);
                    System.out.println("Sorted data: " + Colors.ANSI_PURPLE + Arrays.toString(sensorDataArray) + Colors.ANSI_RESET);
                }
                // create an object to operate over obtained data
                try
                {
                    Fusion fusionObj = new Fusion(sensorDataArray);
                    if(CommonVar.queueType == QueueType.SyncQueue)
                    {
                        Thread t = new Thread(fusionObj);
                        t.setDaemon(true);
                        t.start();
                    }
                    else
                    {
                        CommonVar.executorService.execute(fusionObj);
                    }
                }
                catch(Exception e)
                {
                    Log.LogError("Error in creating Fusion Thread.");
                }
            }
            catch(UnderflowException e)
            {
                Log.LogError("Underflow Exception in Pop operation.");
            }
            catch(Exception e)
            {
                Log.LogError("Exception in One cycle of Worker " + e + " Message: " + e.getMessage());
            }
            
            System.out.println("Worker running..\n");
            try
            {
                Thread.sleep(CommonVar.pollingTime);
            }
            catch (InterruptedException e)
            {
                System.out.println("Current thread not slept.");
            }
        }
    }

    public static void main(String args[])
    {
        CommonVar.queueType = QueueType.values()[Integer.parseInt(args[0])];
        CommonVar.sortRequired = SortOrNot.values()[Integer.parseInt(args[1])];
        System.out.println("Proceeding with: " + CommonVar.queueType + " and " + CommonVar.sortRequired);    
        try
        {
            Log.Initialize();
            Log.LogInfo("Logger Initialized.\n");
            Log.LogInfo("\033[0m BLABLA \033[0m\n");
        }
        catch(Exception e)  
        {
            System.out.println("Logger not initialised. Error:  " + e);
        }
        try
        {
            if(CommonVar.queueType == QueueType.SyncQueue)
            {
                CommonVar.synchronizedQueue = new SyncQueue<Integer>(CommonVar.sensorsCount, CommonVar.queueMaxSize);
                CommonVar.rawSensorDataQueue = new SyncQueue<RawSensorData>(1, CommonVar.rawSensorDataQueueSize);
            }
            else
            {
                CommonVar.synchronizedQueue = new ConcQueue<Integer>(CommonVar.sensorsCount, CommonVar.queueMaxSize);
                CommonVar.rawSensorDataQueue = new ConcQueue<RawSensorData>(1, CommonVar.rawSensorDataQueueSize);
            }
        }
        catch(Exception e)
        {
            Log.LogError("SyncQueue not Initialized. Exception. " + e);
            return;
        }
        
         
        
        for (int i = 0; i <CommonVar.sensorsCount; i++)
        {
            try
            {
                Sensor s = new Sensor(i);
                if(CommonVar.queueType == QueueType.SyncQueue)
                {
                    Thread t = new Thread(s);
                    t.setDaemon(true);
                    t.start();
                }
                else
                {
                    CommonVar.executorService.execute(s);
                }
            }
            catch(Exception e)
            {
                Log.LogError("Error in starting sensor " + i + "Exception: " + e + "Message: " + e.getMessage());
            }
        }
        Log.LogInfo("All Sensors initialized");
        
        // Initialize preProcess Thread.
        PreProcess preProcessRunnable = new PreProcess();
        if(CommonVar.queueType == QueueType.SyncQueue)
        {
            Thread t = new Thread(preProcessRunnable);
            t.setDaemon(true);
            t.start();
        }
        else
        {
            CommonVar.executorService.execute(preProcessRunnable);
        }
        
        try 
        {
            Worker();
        }
        catch(Exception e) 
        {
            Log.LogError("exception or interruption in Worker.. \n" + e + "Message: " + e.getMessage());
        }
    }
}