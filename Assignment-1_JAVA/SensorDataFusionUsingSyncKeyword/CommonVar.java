/*
    author: Mayank, Aishwarya;
    Date:   11th September, 2016;
*/

package SensorDataFusionUsingSyncKeyword;

import java.util.concurrent.*;
// import java.util.*;

/**
 * variables that are common knowledge to all threads
*/
public class CommonVar
{
    // Number of sensors.
    public static final int sensorsCount = 10;

    // Time in ms between 2 consecutive sensor readings to be emitted by sensor.
    public static final int sensorTimespan = 1000;
    
    // Tme diff. between 2 successive worker cycles.
    public static final int pollingTime = 1000;
    
    // Max size of an individual queue
    public static final int queueMaxSize = 5;
    
    // length of sensor reading
    public static final Integer readingLength = 8;
    
    // Using either SyncQueue or Blocking Queue
    public static QueueType queueType;
    
    // Sort the obtained data or not
    public static SortOrNot sortRequired;
    
    // Synchronized queue object to store sensors readings in integer. 
    public static SensorDataQueue synchronizedQueue; 
    
    public static final ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static SensorDataQueue rawSensorDataQueue;
    
    public static final Integer rawSensorDataQueueSize = 100;
}