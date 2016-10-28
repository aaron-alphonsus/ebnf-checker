/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Term rule defined as &lt;term&gt; -&gt; &lt;factor&gt; { &lt;mulop&gt; &lt;factor&gt; }
*/
public class TermRule extends BnfRule {
    
    /**
    *   Gets the name of the rule -- 'term'
    *
    *   @return The string 'term'
    */
    public String name() {
        return "term";
    }
    
    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;term&gt; -&gt; &lt;factor&gt; { &lt;mulop&gt; &lt;factor&gt; }
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    //<term> -> <factor> { <mulop> <factor> }
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        int addition = 0;
        int subindex = 0;
        
        BnfRule factor = rules.get("factor");
        BnfRule mulop = rules.get("mulop");
        
        if(factor == null || mulop == null) return 0;
        
        addition = factor.charsUsed(expr, index+subindex, rules);
        while(addition > 0)
        {
            subindex += addition;
            int mulsize=0, factorsize = 0;
            mulsize = mulop.charsUsed(expr, index+subindex, rules, true);
            if(mulsize > 0)
            {
                factorsize = factor.charsUsed(expr, index+subindex+mulsize, rules);
            }
            if(factorsize > 0)
                addition = mulsize + factorsize;
            else addition = 0;
        }

        return subindex;
    }
    
}
