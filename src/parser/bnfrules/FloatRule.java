/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Float rule defined as &lt;float&gt; -&gt; &lt;integer&gt; . &lt;integer&gt;
*/
public class FloatRule extends BnfRule {

    
    /**
    *   Gets the name of the rule -- 'float'
    *
    *   @return The string 'float'
    */
    public String name() {
        return "float";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;float&gt; -&gt; &lt;integer&gt; . &lt;integer&gt;
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
        BnfRule integer = rules.get("integer");	
        if (integer == null) return 0;    

        // Gets initial integer, stores value in addition
        addition = integer.charsUsed(expr, index+subIndex, rules, true);
        
        // If an integer was found
        if (addition > 0) {
            
            // If we have stepped off the expr array, return 0
            subIndex += addition;
			if(index+subIndex >= expr.length()) return 0;
            
            // Look for the decimal point immediately after the first integer
            addition = ( (expr.charAt(index+subIndex) == '.')? 1 : 0 );
            if (addition > 0) {
                subIndex += addition;
                // Look for the integer after the decimal point
                addition = integer.charsUsed(expr, index+subIndex, rules, true);
		        // If there was an integer, add the index to be returned
				if (addition > 0)
					subIndex += addition;
				else
					return 0;
            }
        }
        
        return subIndex;
    }
    
}
