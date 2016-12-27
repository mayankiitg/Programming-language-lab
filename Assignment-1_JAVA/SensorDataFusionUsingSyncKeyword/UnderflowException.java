/*
    author: Mayank, Aishwarya;
    Date:   21st August 2016;
*/

package SensorDataFusionUsingSyncKeyword;

public class UnderflowException extends Exception
{
    UnderflowException()
    {
        super("Queue Underflow");
    }
}