/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Integer rule defined as &lt;integer&gt; -&gt; &lt;digit&gt; { &lt;digit&gt; }
*/
public class IntegerRule extends BnfRule {

    /**
    *   Gets the name of the rule -- 'integer'
    *
    *   @return The string 'integer'
    */
    public String name() {
        return "integer";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;integer&gt; -&gt; &lt;digit&gt; { &lt;digit&gt; }
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        
        int subIndex = 0;
        int addition = 0;
        
        debug("Begin at " + index);
        BnfRule digit = rules.get("digit");	
        if (digit == null) return 0;    

        // Look for the first digit
        addition = digit.charsUsed(expr, index+subIndex, rules, true);
        // While we keep finding a digit
        while(addition > 0)
        {
            subIndex += addition;
            addition = digit.charsUsed(expr, index+subIndex, rules, true);
        }
        
        // subIndex stores the number of characters used by the rule
        return subIndex;
    }
    
}
