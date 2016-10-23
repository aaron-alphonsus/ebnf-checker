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
public class IdRule extends BnfRule {
    
    public String name() {
        return "id";
    }

    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        int subIndex = 0;
        int addition = 0;
        
        BnfRule letter = rules.get("letter");
        BnfRule digit = rules.get("digit");
        if(letter == null || digit == null) return 0;
        
        //Skip leading whitespace
        if(!keepWhitespace)
            index = skipWhitespace(expr, index);
        
        addition = letter.validTokens(expr, index+subIndex, rules, true);
        while(addition > 0)
        {
            subIndex += addition;
            
            addition = Math.max(letter.validTokens(expr, index+subIndex, rules, true),
                                digit.validTokens(expr, index+subIndex, rules, true));
        }
        return subIndex;
    }
    
}
