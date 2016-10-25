package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class AddOpRule extends BnfRule {

    public String name() {
        return "addop";
    }

    //<addop> -> + | -
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        int whitespace=0;
        int origIndex = index;
        //Skip leading whitespace
        if(!keepWhitespace)
            index = skipWhitespace(expr, index);
    
        whitespace = index-origIndex;
        
        if(index >= expr.length()) return 0;
        if(expr.charAt(index) == '+' ||
        	expr.charAt(index) == '-') return whitespace+1;
    
        return 0;
    }

}
    

























