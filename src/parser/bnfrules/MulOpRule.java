/* For package and program info, please run javadoc */
package parser.bnfrules;
import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the MulOp rule defined as &lt;mulop&gt;   -&gt; * | / | %
*/
public class MulOpRule extends BnfRule {
    
    /**
    *   Gets the name of the rule -- 'mulop'
    *
    *   @return The string 'mulop'
    */
    public String name() {
        return "mulop";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;mulop&gt;   -&gt; * | / | %
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    //<mulop> -> * | / | %
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        
        if(index >= expr.length()) return 0;
        if(expr.charAt(index) == '*' ||
           expr.charAt(index) == '/' ||
           expr.charAt(index) == '%') return 1;
        
        return 0;
    }
    
}
