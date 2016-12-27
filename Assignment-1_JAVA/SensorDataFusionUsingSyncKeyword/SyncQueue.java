/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

import java.lang.*;
import java.util.*;
import Logger.*;

/**
 * Provides Push and pop operations
 * for a synchronized queue
 */
public class SyncQueue<T> implements SensorDataQueue<T>
{
    private LinkedList<T>[] QueueArray;
    
    private int queueMaxSize;
    
    private int arraySize; 
    
    public SyncQueue(int arraySize, int queueSize)
    {
        Log.LogInfo("SyncQueue initialised.");
        this.queueMaxSize = queueSize;
        this.arraySize = arraySize;
        this.QueueArray = new LinkedList[arraySize];
        for(int i=0; i<arraySize; i++)
        {
            this.QueueArray[i] = new LinkedList<T>();
        }
    }
    
    /**
     * takes input queue ID and data
     * push the data to corresponding queue
    */ 
    @Override
    public synchronized void Push(int queueIndex, T data) throws OverflowException
    {
        Log.LogInfo("Push SyncQueue: QueueIndex-" + queueIndex + " Data-" + data.toString() + " QueueSize-" + this.QueueArray[queueIndex].size());
        
        if(this.QueueArray[queueIndex].size() < this.queueMaxSize)
        {
            try
            {
                this.QueueArray[queueIndex].addLast(data);
                Log.LogInfo("SyncQueueArray: " + Arrays.toString(this.QueueArray));
            }
            catch(Exception e)
            {
                Log.LogError("Push SyncQueue Failed Exception: " + e + " Message: " + e.getMessage());
            }
            return;
        }
        throw new OverflowException();
    }
    
    /**
     * completely synchronized function
     * returns array if integer from each queue
     * throws exception if atleast 1 queue is empty
    */
    @Override
    public synchronized T[] Pop() throws UnderflowException
    {
        T[] popFromEachQueue = (T[]) new Object[this.arraySize];
        for(int i=0; i<this.arraySize; i++)
        {
            if(this.QueueArray[i].isEmpty())
            {
                Log.LogError("Pop SyncQueue Unsuccessful. QueueIndex: " + i + " is empty.");
                throw new UnderflowException();
            }
        }
        for(int i=0; i<this.arraySize; i++)
        {
            popFromEachQueue[i] = this.QueueArray[i].poll();
        }
        Log.LogInfo("SyncQueueArray: " + Arrays.toString(this.QueueArray));
        return popFromEachQueue;
    }
    
    @Override
    public synchronized int Size(int queueIndex)
    {
        return this.QueueArray[queueIndex].size();
    }
}