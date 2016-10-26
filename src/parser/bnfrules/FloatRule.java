package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class FloatRule extends BnfRule {
    
    public String name() {
        return "float";
    }

    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        
        int subIndex = 0;
        int addition = 0;
        
        BnfRule integer = rules.get("integer");	
        if (integer == null) return 0;    

        addition = integer.charsUsed(expr, index+subIndex, rules, true);
        if (addition > 0) {
            subIndex += addition;
			if(index+subIndex >= expr.length()) return 0;
            addition = ( (expr.charAt(index+subIndex) == '.')? 1 : 0 );
            if (addition > 0) {
                subIndex += addition;
                addition = integer.charsUsed(expr, index+subIndex, rules, true);
				if (addition > 0)
					subIndex += addition;
				else
					return 0;
            }
        }
        
        return subIndex;
    }
    
}
