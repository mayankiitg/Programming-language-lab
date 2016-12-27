/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

// WHAT HAPPENS WHEN THE QUEUE OVERFLOWS

package SensorDataFusionUsingSyncKeyword;

import Logger.*;

public class PreProcess implements Runnable
{
    public int BinaryStringToInt(String s)
    {
        return Integer.parseInt(s, 2);
    }
    
    // Reimplimentation of run method of Thread class.
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                RawSensorData rawData = (RawSensorData) (CommonVar.rawSensorDataQueue.Pop()[0]);
                Integer sensorReadingInInt = BinaryStringToInt(rawData.data);
                CommonVar.synchronizedQueue.Push(rawData.sensorId, sensorReadingInInt);
            }
            catch(UnderflowException | OverflowException e)
            {
                try
                {
                    Thread.sleep(CommonVar.sensorTimespan);
                }
                catch(InterruptedException e2)
                {
                    Log.LogError("Unable to sleep thread PreProcess");
                }
            }
        }
    }
}