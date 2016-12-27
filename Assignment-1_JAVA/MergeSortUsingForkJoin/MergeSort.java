/*
    author: Mayank;
    Date:   4th September 2016;
*/

package MergeSortUsingForkJoin;


import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import Logger.*;
import java.io.*;
import java.text.*;

class MergeSortTask extends RecursiveAction
{
    Integer arr[], lo, hi;
    public MergeSortTask(Integer[] arr, Integer l, Integer r)
    {
        this.arr = arr;
        this.lo = l;
        this.hi = r;
    }
    
    void merge()
    {
        Integer[] tempArr = new Integer[hi- lo +1];
        Integer mid = (lo + hi)/2;
        Integer k = 0, l = lo, r = mid + 1;
        while(l <= mid || r <= hi)
        {
            if(l <= mid && r <= hi)
            {
                if(arr[l] < arr[r])
                {
                    tempArr[k++] = arr[l++];
                }
                else
                {
                    tempArr[k++] = arr[r++];
                }
            }
            else if(l <= mid)
            {
                tempArr[k++] = arr[l++];
            }   
            else
            {
                tempArr[k++] = arr[r++];
            }
        }
        for(int i=0;i<k;i++)
        {
            arr[lo+i] = tempArr[i];
        }
    }
    
    protected void compute()
    {
        // System.out.println("Thread: " + Thread.currentThread().getId());
        
        if (lo >= hi)
        {
            return;
        }
        
        int mid = (lo + hi)/2;
        try
        {
            invokeAll(new MergeSortTask(arr, lo, mid), new MergeSortTask(arr, mid+1, hi));
            /*
            MergeSortTask task1 = new MergeSortTask(arr, lo, mid);
            MergeSortTask task2 = new MergeSortTask(arr, mid+1, hi);
            task1.fork();
            task2.fork();
            task1.join();
            task2.join();
            */
            
            merge();
        }
        catch(Exception ex)
        {
            Log.LogError("Error in compute: " + ex.getCause());                
        }
    }
    
}


public class MergeSort
{
    
    public static void Sort(Integer[] arr)
    {
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(arr, 0, arr.length-1);
        pool.invoke(task);  
    }
    
    public static void main(String args[])
    {
        int[] arrToSort = {2, 3, 1, 4, 5};
        try
        {
            Log.Initialize();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Logger initialisation error." + ex.getCause());
        }
        // MergeSort.Sort(arrToSort);
        // System.out.println("Line: " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        // System.out.println("Line: " + Arrays.toString(Thread.currentThread().getStackTrace()));
        // ForkJoinPool pool = new ForkJoinPool();
        // MergeSortTask task = new MergeSortTask(arrToSort, 0, arrToSort.length-1);
        // pool.invoke(task);
    
        
        // for(int i=0;i<arrToSort.length;i++)
        // {
        //     System.out.println(arrToSort[i]);
        //     Log.LogInfo(Integer.toString(arrToSort[i]));
        // }
    }
}