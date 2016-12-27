/*
    author: Mayank, Aishwarya;
    Date:   23rd August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import Logger.*;
import MergeSortUsingForkJoin.*;

/**
 * Provides Push and pop operations
 * for a concurrent (here, blocking queue) queue
 */

public class ConcQueue<T> implements SensorDataQueue<T>
{
    private ArrayList<BlockingQueue<T>> BlockingQueueArray;
    
    private int queueMaxSize;
    
    private int arraySize; 
    
    public ConcQueue(int arraySize, int queueSize)
    {
        Log.LogInfo("ConcQueue initialised.");
        this.queueMaxSize = queueSize;
        this.arraySize = arraySize;
        this.BlockingQueueArray = new ArrayList<BlockingQueue<T>>(arraySize);
        for(int i=0; i<arraySize; i++)
        {
            this.BlockingQueueArray.add(new ArrayBlockingQueue<T>(queueSize));
        }
    }
    /**
     * takes input queue ID and data
     * push the data to corresponding queue
    */ 
    @Override
    public void Push(int queueIndex, T data) throws OverflowException
    {
        Log.LogInfo("Push ConcQueue: QueueIndex-" + queueIndex + " Data-" + data.toString() + " QueueSize-" + this.BlockingQueueArray.get(queueIndex).size());
        if(this.BlockingQueueArray.get(queueIndex).size() < this.queueMaxSize)
        {
            try
            {
                this.BlockingQueueArray.get(queueIndex).put(data);
                Log.LogInfo("ConcQueueArray: " + this.BlockingQueueArray);
            }
            catch(Exception e)
            {
                Log.LogError("Push ConcQueue Failed Exception: " + e + " Message: " + e.getMessage());
            }
            return;
        }
        throw new OverflowException();
    }
    /**
     * returns array if integer from each queue
     * throws exception if atleast 1 queue is empty
    */
    @Override
    public T[] Pop() throws UnderflowException
    {
        T[] popFromEachQueue = (T[]) new Object[this.arraySize];
        for(int i=0; i<this.arraySize; i++)
        {
            if(this.BlockingQueueArray.get(i).isEmpty())
            {
                Log.LogError("Pop ConcQueue Unsuccessful. QueueIndex: " + i + " is empty.");
                throw new UnderflowException();
            }
        }
        for(int i=0; i<this.arraySize; i++)
        {
            popFromEachQueue[i] = this.BlockingQueueArray.get(i).poll();
        }
        Log.LogInfo("ConcQueueArray: " + this.BlockingQueueArray);
        return popFromEachQueue;
    }
    
    @Override
    public int Size(int queueIndex)
    {
        return this.BlockingQueueArray.get(queueIndex).size();
    }
}