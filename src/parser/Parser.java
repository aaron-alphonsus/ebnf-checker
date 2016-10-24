/*
Main file of the program. See package-info.java or javadoc
documentation for full program description

javadoc -d ./html parser bnfchecker parser.bnfrules
*/
package parser;

import bnfchecker.BnfCheckerResult;
import bnfchecker.BnfChecker;
import java.util.Scanner;
import parser.bnfrules.*;

/**
 * Main program class. Contains main function (Program entry point)
 * and a function to set up the {@link BnfChecker} object used
 * to check input expressions.
 *
 * @author Andrew Stelter
 * @author Aaron Alphonsus
 */
public class Parser {

    /**
     * Program entry point. Checks command line arguments for
     * '-t' and '-b', then goes into an infinite loop of the form
     * "read, evaluate, output" until the user inputs a blank string.
     * For each inputted string is passed to the BnfChecker which has been
     * set up. The BnfChecker returns how many characters were successfully
     * matched for any rule. If that number is the same as the number of
     * characters in the input string, then it was a valid input.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Set up input scanner
        Scanner cin = new Scanner( System.in );
        String input;
        
        //Build the Bnf rule checker
        BnfChecker checker = setupChecker();
        
        //Flags for command line inputs
        boolean tFlag = false, bFlag = false;
        
        //Check all arguments to be 
        for(String s : args)
        {
            if(s.equals("-b")) bFlag = true;
            if(s.equals("-t")) tFlag = true;
        }
        
        //Loop of read, evaluate, output until
        //an empty string is inputted
        do
        {
            //Prompt user
            System.out.print("Enter Expression: ");
            
            //Read a line
            input = cin.nextLine();
            
            //If empty string, exit
            if(input.trim().length() == 0)
                return;
            
            //Check the expression
            BnfCheckerResult result = checker.checkExpression(input);

            //See if all characters were used to complete a rule
            if(result.chars() == input.length())
                System.out.println("\"" + input + "\" is a valid expression");
            else
                System.out.println("\"" + input + "\" is not a valid expression");
            
            //-t specified, output tokens
            if(tFlag)
            {
                System.out.print("Tokens: ");
                for(String s : result.tokens())
                {
                    if(!s.equals(" "))
                        System.out.print("\"" + s + "\""  + " ");
                }
                System.out.print("\n");
            }
            
            //-b specified, output best matching rule
            if(bFlag)
            {
                System.out.println("Best Match: " + result.name());
                if(result.chars() != input.length())
                    System.out.println(result.chars() + " characters used");
            }
        }while(true);
    }
    
   /**
    * Function to create the {@link BnfChecker} object
    * and insert all of the {@link bnfchecker.BnfRule}s to be used
    * to check inputs
    *
    * @return A {@link BnfChecker} object loaded with all
    * of the {@link bnfchecker.BnfRule}s to use
    */
    public static BnfChecker setupChecker()
    {
        BnfChecker check = new BnfChecker();
        
        check.addRule(new ExprRule());
        check.addRule(new TermRule());
        
        check.addRule(new IntegerRule());
        check.addRule(new FloatRule());
        check.addRule(new IdRule());
        check.addRule(new LetterRule());
        check.addRule(new DigitRule());
        check.addRule(new AddOpRule());
        check.addRule(new MulOpRule());
        
        
        return check;
    }
}
