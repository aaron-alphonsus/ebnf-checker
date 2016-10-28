/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Expr rule defined as &lt;expr&gt; -&gt; &lt;term&gt;   { &lt;addop&gt; &lt;term&gt; }
*/
public class ExprRule extends BnfRule {

    /**
    *   Gets the name of the rule -- 'expr'
    *
    *   @return The string 'expr'
    */
    public String name() {
        return "expr";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule  &lt;expr&gt; -&gt; &lt;term&gt;   { &lt;addop&gt; &lt;term&gt; }
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    //<expr> -> <term> { <addop> <term> }
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        int addition = 0;
        int subindex = 0;
        
        debug("Begin at " + index);
        BnfRule term = rules.get("term");
        BnfRule addop = rules.get("addop");
        if(term == null || addop == null) return 0;
        
        debug("Recurse to term");
        addition = term.charsUsed(expr, index+subindex, rules);
        while(addition > 0)
        {
            subindex += addition;
            int addsize=0, termsize = 0;
            
            debug("Recurse to addop");
            addsize = addop.charsUsed(expr, index+subindex, rules);
            if(addsize > 0)
            {
                debug("Recurse to term");
                termsize = term.charsUsed(expr, index+subindex+addsize, rules);
            }
            if(termsize > 0)
                addition = addsize + termsize;
            else addition = 0;
        }

        return subindex;
    }
    
}
