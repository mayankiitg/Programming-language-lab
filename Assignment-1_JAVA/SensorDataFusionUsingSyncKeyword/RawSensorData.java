/*
    author: Mayank, Aishwarya;
    Date:   11th September 2016;
*/

package SensorDataFusionUsingSyncKeyword;

/**
 * Represents sensor data. 
 * Needed to insert and removing from rawSensorDataQueue.
 */
public class RawSensorData
{
    int sensorId;
    
    String data;
    
    public RawSensorData(int sId, String data)
    {
        this.sensorId = sId;
        this.data = data;
    }
    
    @Override
    public String toString()
    {
        return "(" + this.sensorId + ", " + this.data + ")";
    }
}