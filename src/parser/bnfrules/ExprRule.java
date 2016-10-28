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
        
        //Make sure the necessary recursion rules exist
        if(term == null || addop == null) return 0;
        
        debug("Recurse to term");
        
        //Check if the term rule can be done
        addition = term.charsUsed(expr, index+subindex, rules, true);
        
        //As long as the <addop> <term> pair is valid, look for it again
        while(addition > 0)
        {
            //Add in the amount used by the previous rule(s)
            subindex += addition;
            int addsize=0, termsize = 0;
            
            debug("Recurse to addop");
            //Check for an addop
            addsize = addop.charsUsed(expr, index+subindex, rules);
            
            //If there's an addop, check for a term
            if(addsize > 0)
            {
                debug("Recurse to term");
                termsize = term.charsUsed(expr, index+subindex+addsize, rules);
            }
            
            //if there's a term, then the <addop> <term> pair is valid
            if(termsize > 0)
                addition = addsize + termsize;
            else addition = 0;
        }

        return subindex;
    }
    
}
