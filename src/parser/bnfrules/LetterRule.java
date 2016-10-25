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

/*<letter> -> A | B | C | D | E | F | G | H | I | J | K | L | M |
              N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
              a | b | c | d | e | f | g | h | i | j | k | l | m |
              n | o | p | q | r | s | t | u | v | w | x | y | z | _ */
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        int origIndex = index;
        
        //Skip leading whitespace
        if(!keepWhitespace)
            index = skipWhitespace(expr, index);
        
        int whitespace = index-origIndex;
        
        //If first token > 1 characters, not a digit
        if (index >= expr.length()) return 0;
        //Get the character
        char candidate = expr.charAt(index);
        
        //Check if the token character is a letter or _
        if(candidate >= 'a' && candidate <= 'z' ||
           candidate >= 'A' && candidate <= 'Z' ||
           candidate == '_') return 1+whitespace;
        
        return 0;
    }
    
}
