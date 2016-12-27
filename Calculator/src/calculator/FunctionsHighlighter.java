/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.Arrays;

/**
 * Contain logic to highlight the functions of the calculator interface.
 * @author mayank, aishwarya
 */
class FunctionsHighlighter implements Runnable
{
    /**
     * Runs infinitely.
     * Every Second change the color of functions labels sequentially from + to /.
     */
    @Override
    public void run()
    {
        int i=0;
        while(true)
        {
            CalculatorGUI.setFunctionColor((i+3)%4, new java.awt.Color(187, 138, 246));
            CalculatorGUI.setFunctionColor(i, new java.awt.Color(20, 220, 20));
            i++;
            i = i % 4;
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
