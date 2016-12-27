/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Contain helper methods.
 * @author mayank, aishwarya
 */
public class HelperMethods {
    
    
    /**
     * Calculate an arithmetic expression using postfix evaluation.
     * @param content the arithmetic expression to be evaluated.
     * @return the result of expression as string.
     */
    public static String CalculateExpression(String content)
    {
        System.out.println("Calculating Expression: " + content);
        if(content.charAt(0) == '+' || content.charAt(0) == '-')
            content = "0" + content;
        // Stores the operators of expression.
        Stack<Character> s = new Stack<Character>();
        
        // stores the partially evaluated expressions.
        Stack<Double> s1 = new Stack<Double>();           
        
        Double num1, num2;
        String postfix = "";
        for(int i=0;i<content.length();)
        {
            if(content.charAt(i) == ' ')
            {
                i++;
                continue;
            }
            if(content.charAt(i) >= '0' && content.charAt(i) <= '9')
            {
                String temp ="";
                while(i < content.length() && (content.charAt(i) == '.' ||(content.charAt(i) >= '0' && content.charAt(i) <= '9')))
                    temp += content.charAt(i++);
                s1.push(Double.parseDouble(temp));
                postfix += temp;
            }
            else if(content.charAt(i) == '+' || content.charAt(i) == '-')
            {
                while(!s.empty())
                {
                    try
                    {
                        num2 = s1.pop();
                        num1= s1.pop();
                    }
                    catch (EmptyStackException e)
                    {
                        return null;
                    }
                    switch(s.peek())
                    {
                        case '+' :  s1.push(num1+num2);
                                    break;
                        case '-' :  s1.push(num1-num2);
                                    break;
                        case '*' :  s1.push(num1*num2);
                                    break;
                        case '/' :  s1.push(Math.round((num1/num2) * 100)/100.0);
                                    break;
                    }
                    postfix += s.peek();
                    s.pop();
                }
                s.push(content.charAt(i++));
            }
            else if(content.charAt(i) == '*' || content.charAt(i) == '/')
            {
                while(!s.empty() && (s.peek() == '*' || s.peek() == '/'))
                {
                    try
                    {
                        num2 = s1.pop();
                        num1= s1.pop();
                    }
                    catch (EmptyStackException e)
                    {
                        return null;
                    }
                    switch(s.peek())
                    {
                        case '+' :  s1.push(num1+num2);
                                    break;
                        case '-' :  s1.push(num1-num2);
                                    break;
                        case '*' :  s1.push(num1*num2);
                                    break;
                        case '/' :  s1.push(Math.round((num1/num2) * 100)/100.0);
                                    break;
                    }
                    postfix += s.peek();
                    s.pop();
                }
                s.push(content.charAt(i++));
            }
        }
        while(!s.empty())
        {
            try
            {
                num2 = s1.pop();
                num1= s1.pop();
            }
            catch (EmptyStackException e)
            {
                return null;
            }
            switch(s.peek())
            {
                case '+' :  s1.push(num1+num2);
                            break;
                case '-' :  s1.push(num1-num2);
                            break;
                case '*' :  s1.push(num1*num2);
                            break;
                case '/' :  s1.push(Math.round((num1/num2) * 100)/100.0);
                            break;
            }
            postfix += s.peek();
            s.pop();
        }
        System.out.println(postfix);
        try
        {
            Double out = s1.pop();
            if(!s1.empty())
                return null;
            return String.valueOf(out);
        }
        catch(EmptyStackException e)
        {
            return null;
        }
    }
}
