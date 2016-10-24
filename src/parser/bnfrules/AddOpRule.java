package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class AddOpRule extends BnfRule {

    public String name() {
        return "addop";
    }

    //<addop> -> + | -
	public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
		
        if(index >= expr.length()) return 0;

        //Skip leading whitespace
        if(!keepWhitespace)
            index = skipWhitespace(expr, index);
    
        if(expr.charAt(index) == '+' ||
        	expr.charAt(index) == '-') return 1;
    
        return 0;
    }

}
    

























