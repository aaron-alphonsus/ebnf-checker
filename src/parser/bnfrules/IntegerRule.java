package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class IntegerRule extends BnfRule {
    
    public String name() {
        return "integer";
    }

    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        
        int subIndex = 0;
        int addition = 0;
        int origIndex = index;
        
        BnfRule digit = rules.get("digit");	
        if (digit == null) return 0;    

        //Skip leading whitespace
        if (!keepWhitespace)        
            index = skipWhitespace(expr, index);
        
        int whitespace = index-origIndex;
        
        addition = digit.validTokens(expr, index+subIndex, rules, true);
        while(addition > 0)
        {
            subIndex += addition;
            addition = digit.validTokens(expr, index+subIndex, rules, true);
        }
        return subIndex+(subIndex>0?whitespace:0);
    }
    
}
