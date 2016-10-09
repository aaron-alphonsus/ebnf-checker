/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Scanner;
import bnfchecker.*;
import parser.bnfrules.*;

/**
 *
 * @author 7280681
 */
public class Parser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner( System.in );
        String input;
        BnfChecker checker = setupChecker();
        boolean tFlag = false, bFlag = false;
        String ruleFile = "";
        
        for(String s : args)
        {
            if(s.charAt(0) == '-')
            {
                if(s.length() > 1)
                {
                    if(s.charAt(1) == 't') tFlag = true;
                    if(s.charAt(1) == 'b') bFlag = true;
                }
            }
            else
            {
                ruleFile = s;
            }
        }
        
        cin.useDelimiter("\n");
        do
        {
            System.out.print("Enter Expression: ");
            input = cin.next();
            if(input.trim().length() == 0)
                return;
            
            BnfCheckerResult result = checker.checkExpression(input);
            if(tFlag)
            {
                System.out.print("Tokens: ");
                for(String s : result.tokens())
                {
                    System.out.print(s + " ");
                }
                System.out.print("\n");
            }
            if(result.chars() == input.length())
                System.out.println("Valid Expression");
            else
                System.out.println("Invalid Expression");
            
            if(bFlag)
            {
                System.out.println("Best Match: " + result.name());
                if(result.chars() != input.length())
                    System.out.println(result.chars() + " characters used");
            }
        }while(true);
    }
    
    private static BnfChecker setupChecker()
    {
        BnfChecker check = new BnfChecker();
        
        check.addRule(new DigitRule());
        check.addRule(new IdRule());
        check.addRule(new LetterRule());
        check.addRule(new MulOpRule());
        
        return check;
    }
}
