/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Arrays;

/**
 * Contain logic to highlight the numbers of the calculator interface.
 * @author mayank, aishwarya
 */
class NumberHighlighter implements Runnable
{
    /**
     * Runs infinitely.
     * Every Second change the color of numbers sequentially from 1 to stop.
     */
    @Override
    public void run()
    {
        int i=0;
        while(true)
        {
            // Reset the color of previous highlighted function.
            CalculatorGUI.setNumberColor((i+10)%11, new java.awt.Color(187, 138, 246));
            // Set color of next function.
            CalculatorGUI.setNumberColor(i, new java.awt.Color(20, 220, 20));
            i++;
            i = i % 11;
            //long expected = System.currentTimeMillis() + 1000;
            try{
            Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
