/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Id rule defined as &lt;id&gt; -&gt; &lt;letter&gt; { &lt;letter&gt; | &lt;digit&gt; }
*/
public class IdRule extends BnfRule {
    
    /**
    *   Gets the name of the rule -- 'id'
    *
    *   @return The string 'id'
    */
    public String name() {
        return "id";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;id&gt; -&gt; &lt;letter&gt; { &lt;letter&gt; | &lt;digit&gt; }
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
        
        BnfRule letter = rules.get("letter");
        BnfRule digit = rules.get("digit");
        if(letter == null || digit == null) return 0;
        
        addition = letter.charsUsed(expr, index+subIndex, rules, true);
        while(addition > 0)
        {
            subIndex += addition;
            
            addition = Math.max(letter.charsUsed(expr, index+subIndex, rules, true),
                                digit.charsUsed(expr, index+subIndex, rules, true));
        }
        return subIndex;
    }
    
}
