/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

import java.lang.*;
import java.util.*;
import Logger.*;

// Represents a Sensor.
// emit sensor readings (8 bit binary string) continuously after each "t" seconds; 
public class Sensor implements Runnable
{
    // Time gap between 2 emissions of reading in milliseconds.
    private static final int readingsEmittingTime = CommonVar.sensorTimespan;
    
    // The Unique Identifier for a sensor.
    private int sensorId;
    
    // Reading of the sensor.
    private String sensorReading;
    
    // Create a new instance of sensor
    public Sensor(int id)
    {
        this.sensorId = id;
        Log.LogInfo("Sensor-" + id + " initialised.");
    }

    // Method Which generates a random 8 bit binary string.
    private void randomlyGenerateReading()
    {
        Random random = new Random();
        this.sensorReading = "";
        for(int i=0; i<CommonVar.readingLength; i++)
        {
            this.sensorReading = this.sensorReading.concat(random.nextBoolean()?"1":"0");
        }
    }

    // Definition of run() func of Thread class.
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                randomlyGenerateReading();
                RawSensorData data = new RawSensorData(this.sensorId, this.sensorReading);
                CommonVar.rawSensorDataQueue.Push(0, data);
                Log.LogInfo("Sensor-" + this.sensorId + " generated reading \"" + this.sensorReading + "\"");
            } 
            catch (OverflowException e) {}
            catch(Exception e2)
            {
                Log.LogError("Sensor-" + this.sensorId + " malfunctioned. " + e2.getMessage());
            }

            try
            {
                Thread.sleep(this.readingsEmittingTime);
            }
            catch(InterruptedException e)
            {
                Log.LogError("Unable to sleep thread Sensor");
            }
        }   
    }
}