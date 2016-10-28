/* For package and program info, please run javadoc */
package bnfchecker;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Object to hold and query {@link BnfRule} objects to
 * attempt to parse an input
 *
 * @author Andrew Stelter
 */
public class BnfChecker {
    private HashMap<String, BnfRule> _rules = new HashMap<String, BnfRule>();
    private boolean _debug;
    
    /**
    * Empty constructor
    */
    public BnfChecker(){}
    
    /**
    * Checks if a String expression matches any {@link BnfRule}
    * contained in this {@link BnfChecker}. If no rule matches, the
    * output will contain "No Rule" and 0 characters, along with
    * the tokens found.
    *
    * @param expr String containing the expression to be checked
    * @return A {@link BnfCheckerResult} with the result of the match
    */
    public BnfCheckerResult checkExpression(String expr)
    {
        //Default rule name and characters used
        String bestMatch = "No Rule";
        int bestChars = 0;
        int charsUsed;
        
        //Tokenize the expression
        ArrayList<String> tokens = tokenize(expr);
        
        //Check each rule contained to see if it can be used
        //To match the entire expression
        for(String s : _rules.keySet())
        {
            //Get the number of characters used for this rule
            charsUsed = _rules.get(s).charsUsed(expr, 0, _rules);
            
            //Update best match if necessary
            if(charsUsed > bestChars)
            {
                bestChars = charsUsed;
                bestMatch = s;
            }
            
            //If any rule uses all tokens, then there is no need to check more
            if(bestChars == expr.length())
                break;
        }
        //Return a BnfCheckerResult with the results
        return new BnfCheckerResult(bestChars, bestMatch, tokens);
    }
    
    /**
    * Add a {@link BnfRule} to the checker to be used
    * for expression checks
    *
    * @param rule A {@link BnfRule} object
    */
    public void addRule(BnfRule rule)
    {
        _rules.put(rule.name(), rule);
    }
    
    /**
    * Tokenizes a string
    *
    * @param expr The String to tokenize
    *
    * @return An ArrayList<String> Object containing all tokens
    * parsed
    */
    private ArrayList<String> tokenize(String expr)
    {
        //Create list to return
        ArrayList<String> tokens = new ArrayList<String>();
        
        //Create tokenizer to split on all operators, ., and ()
        StringTokenizer tokfinder = new StringTokenizer(expr, "*/%+-. \t()", true);
        
        //Read as many tokens as can be found
        String nextToken;
        while(tokfinder.hasMoreTokens())
        {
            nextToken = tokfinder.nextToken();
            
            //Only keep tokens that are not whitespace
            if(nextToken.trim().length() > 0)
            {
                tokens.add(nextToken);
            }
        }
        
        return tokens;
    }
    
    public void setDebugging(boolean debug)
    {
        _debug = debug;
        for(String k : _rules.keySet())
            _rules.get(k).setDebugging(debug);
    }
}
