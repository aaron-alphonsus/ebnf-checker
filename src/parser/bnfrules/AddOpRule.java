package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

public class AddOpRule extends BnfRule {

    public String name() {
        return "addop";
    }

    //<addop> -> + | -
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {

        if(index >= expr.length()) return 0;
        if(expr.charAt(index) == '+' ||
        	expr.charAt(index) == '-') return 1;
    
        return 0;
    }

}
    

























