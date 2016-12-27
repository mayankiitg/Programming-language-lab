/*
    author: Mayank, Aishwarya;
    Date:   23rd August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

public interface SensorDataQueue<T>
{   
    public void Push(int queueIndex, T data) throws OverflowException ;
    
    public T[] Pop() throws UnderflowException;
    
    public int Size(int queueIndex);
}