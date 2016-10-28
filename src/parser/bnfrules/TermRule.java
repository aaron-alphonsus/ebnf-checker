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
        
        debug("Begin at " + index);
        BnfRule factor = rules.get("factor");
        BnfRule mulop = rules.get("mulop");
        
        //Make sure the necessary recursion rules exist
        if(factor == null || mulop == null) return 0;
        
        debug("Recurse to factor");
        
        //Check for a factor
        addition = factor.charsUsed(expr, index+subindex, rules, true);
        
        //Continue checking for <mulop> <factor> pairs
        while(addition > 0)
        {
            subindex += addition;
            int mulsize=0, factorsize = 0;
            
            debug("Recurse to mulop");
            
            //Check for a mulop
            mulsize = mulop.charsUsed(expr, index+subindex, rules);
            
            //If there's a mulop, check for a factor
            if(mulsize > 0)
            {
                debug("Recurse to factor");
                factorsize = factor.charsUsed(expr, index+subindex+mulsize, rules);
            }
            
            //If there's a factor, the <mulop> <factor> pair is valid
            if(factorsize > 0)
                addition = mulsize + factorsize;
            else addition = 0;
        }

        return subindex;
    }
    
}
