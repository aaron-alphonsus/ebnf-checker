/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Digit rule defined as &lt;digit&gt; -&gt; 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
*/
public class DigitRule extends BnfRule {
    
    /**
    *   Gets the name of the rule -- 'digit'
    *
    *   @return The string 'digit'
    */
    public String name() {
        return "digit";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;digit&gt; -&gt; 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    //<digit> -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        //If first token > 1 characters, not a digit
        if (index >= expr.length()) return 0;
        
        //Get the character
        char candidate = expr.charAt(index);
        
        //Check if the token character is between '0' and '9'
        if(candidate >= '0' && candidate <= '9') return 1;
        return 0;
    }
    
}
