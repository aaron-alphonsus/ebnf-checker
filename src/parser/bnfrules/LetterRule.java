/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 7280681
 */
public class LetterRule extends BnfRule {
    
    public String name() {
        return "letter";
    }

    public int validTokens(ArrayList<String> tokens, int index, HashMap<String, BnfRule> rules) {
        //If first token > 1 characters, not a digit
        if (index >= tokens.size() || tokens.get(index).length() > 1) return 0;
        
        //Get the character
        char candidate = tokens.get(index).charAt(0);
        
        //Check if the token character is a letter or _
        if(candidate >= 'a' && candidate <= 'z' ||
           candidate >= 'A' && candidate <= 'Z' ||
           candidate == '_') return 1;
        
        return 0;
    }
    
}
