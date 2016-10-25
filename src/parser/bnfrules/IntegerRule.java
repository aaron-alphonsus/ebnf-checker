package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class IntegerRule extends BnfRule {
    
    public String name() {
        return "integer";
    }

    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        
        int subIndex = 0;
        int addition = 0;
        
        BnfRule digit = rules.get("digit");	
        if (digit == null) return 0;    

        addition = digit.charsUsed(expr, index+subIndex, rules, true);
        while(addition > 0)
        {
            subIndex += addition;
            addition = digit.charsUsed(expr, index+subIndex, rules, true);
        }
        return subIndex;
    }
    
}
