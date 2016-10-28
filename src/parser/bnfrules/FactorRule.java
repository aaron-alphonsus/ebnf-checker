/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Factor rule defined as
&lt;factor&gt; -&gt; &lt;integer&gt; | &lt;float&gt; | &lt;id&gt; | '(' 
&lt;expr&gt; ')' | -&lt;factor&gt;
*/
public class FactorRule extends BnfRule {
    
    /**
    *   Gets the name of the rule -- 'factor'
    *
    *   @return The string 'factor'
    */
    public String name() {
        return "factor";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few 
    * characters fit the rule &lt;factor&gt; -&gt; &lt;integer&gt; | 
    * &lt;float&gt; | &lt;id&gt; | '(' &lt;expr&gt; ')' | -&lt;factor&gt;
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        
        debug("Begin at " + index);
        int indInteger = 0;
        int indFloat = 0;
        int indId = 0;
        int indExpr = 0;
        int indFactor = 0;
        int newInd = 0;
        
        BnfRule integer = rules.get("integer");
        BnfRule floatrule = rules.get("float");
        BnfRule id = rules.get("id");
        BnfRule exprrule = rules.get("expr");
        BnfRule factor = rules.get("factor");
        if(integer == null || floatrule == null || id == null || 
            exprrule == null || factor == null) return 0;
        
        // Start searching for each element. Store largest number of characters 
        // that match in each ind variable
        
        // Validating <integer>
        debug("Recurse to integer");
        indInteger = integer.charsUsed(expr, index, rules);
        
        // Validating <float>
        debug("Recurse to float");
        indFloat = floatrule.charsUsed(expr, index, rules);
        
        // Validating <id>
        debug("Recurse to id");
        indId = id.charsUsed(expr, index, rules);
        
        // Validating (<expr>). 
        if(index < expr.length()) {
            // Searches for a '(' first
            if (expr.charAt(index) == '(') {
                debug("Recurse to expr");
                // Then, looks for an expr
                indExpr = exprrule.charsUsed(expr, index+1, rules);
                // If an expr was found
                if (indExpr > 0) {
                    // skips whitespace and looks for a ')'
                    newInd = skipWhitespace(expr, index+indExpr+1);
                    if (newInd < expr.length() && 
                        expr.charAt(newInd) == ')')
                        indExpr = newInd - index + 1;
                    else
                        indExpr = 0;
                }
            }
        }   
        
        // Validating -<factor>. 
        // Searches for a '-' first
        if (index < expr.length() && expr.charAt(index) == '-') {
            debug("Recurse to factor");
            // Then, calls factor on characters following the '-'
            indFactor = factor.charsUsed(expr, index+1, rules, false);
            if (indFactor > 0)
                indFactor += 1;
        }
        
        // Finds the max of all five lengths
        return Math.max(Math.max(indInteger, indFloat), 
            Math.max(indId, 
                Math.max(indExpr, indFactor)));
    }
    
}
