/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Custom key listener which listen for key press events of enter and space keys.
 * 
 * @author mayank
 */
class CustomKeyListener implements KeyListener{
      
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
           CalculatorGUI.NumberSelectEvent();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            CalculatorGUI.FunctionSelectEvent();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }   
}
