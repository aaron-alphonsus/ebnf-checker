package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class FloatRule extends BnfRule {
    
    public String name() {
        return "float";
    }

    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        
        int subIndex = 0;
        int addition = 0;
        
        BnfRule integer = rules.get("integer");	
        if (integer == null) return 0;    

        //Skip leading whitespace
        if (!keepWhitespace)        
            index = skipWhitespace(expr, index);
        
        addition = integer.validTokens(expr, index+subIndex, rules, true);
        if (addition > 0) {
            subIndex += addition;
            addition = ( (expr.charAt(index+subIndex) == '.')? 1 : 0 );
            if (addition > 0) {
                subIndex += addition;
                addition = integer.validTokens(expr, index+subIndex, rules, false);
            }
        }
        
        return subIndex;
    }
    
}
