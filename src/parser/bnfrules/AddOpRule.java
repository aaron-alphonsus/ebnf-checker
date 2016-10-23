package parser.bnfrules;
import bnfchecker.BnfRule;
import java.util.HashMap;

public class AddOpRule extends BnfRule {

    public String name() {
        return "addop";
    }

    //<addop> -> + | -
	public int validTokens(String tokens, int index, HashMap<String, BnfRule> rules) {
		if(index >= tokens.length()) return 0;
    
        if(tokens.charAt(index) == '+' ||
        	tokens.charAt(index) == '-') return 1;
    
        return 0;
    }

}
    

























