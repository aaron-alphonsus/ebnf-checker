/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
 *
 * @author 7280681
 */
public class DigitRule extends BnfRule {
    
    public String name() {
        return "digit";
    }

    //<digit> -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        //If first token > 1 characters, not a digit
        if (index >= expr.length()) return 0;
        
        //Get the character
        char candidate = expr.charAt(index);
        
        //Check if the token character is between '0' and '9'
        if(candidate >= '0' && candidate <= '9') return 1;
        return 0;
    }
    
}
