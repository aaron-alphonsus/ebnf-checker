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
public class LetterRule extends BnfRule {
    
    public String name() {
        return "letter";
    }

    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        //If first token > 1 characters, not a digit
        if (index >= expr.length()) return 0;
        
        //Get the character
        char candidate = expr.charAt(index);
        
        //Check if the token character is a letter or _
        if(candidate >= 'a' && candidate <= 'z' ||
           candidate >= 'A' && candidate <= 'Z' ||
           candidate == '_') return 1;
        
        return 0;
    }
    
}
