/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the AddOp rule defined as &lt;addop&gt; -&gt; + | -
*/
public class AddOpRule extends BnfRule {

    /**
    *   Gets the name of the rule -- 'addop'
    *
    *   @return The string 'addop'
    */
    public String name() {
        return "addop";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;addop&gt; -&gt; + | -.
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    //<addop> -> + | -
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {

        if(index >= expr.length()) return 0;
        if(expr.charAt(index) == '+' ||
        	expr.charAt(index) == '-') return 1;
    
        return 0;
    }

}
    

























