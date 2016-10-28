/* For package and program info, please run javadoc */
package bnfchecker;

import java.util.HashMap;

/**
 * Class which checks uses recursive descent parsing
 * to see if a set of characters matches a bnf rule
 *
 * @author Andrew Stelter
 */
public abstract class BnfRule {

    private boolean _debugging = false;

    /**
    * Set the debug statements from rules
    *
    * @param debug Whether you want debug output or not
    */
    public void setDebugging(boolean debug)
    {
        _debugging = debug;
    }
    
    /**
    * Output function which takes the debugging
    * setting into account
    *
    * @param line The text to output if debug mode is on
    */
    protected void debug(String line)
    {
        if(_debugging) System.out.println(name() + ": " + line);
    }

    /**
    * Return the name of the rule to be used by other
    * rules to recurse to this rule
    *
    * @return A String, which is the name of the rule
    */
    public abstract String name();

    
    /**
    * Checks how many characters of the string, starting at index,
    * can be used to match this rule.
    *
    * @param expr The String expression to evaluate
    * @param index The index of expr to start at, inclusive
    * @param rules A hash of all available rules, hashed by their names
    *
    * @return The number of characters that this rule can use, starting at index.
    *         0 if the rule can't be matched at all
    */
    protected abstract int validTokens(String expr, int index, HashMap<String, BnfRule> rules);
     
    /**
     * Convenience function to call charsUsed with the keepWhitespace param
     * defaulted to false
     * 
     * @param expr The expression to evaluate
     * @param index The index to start at
     * @param rules The list of other rules to use
     * @return The number of characters used for this rule; 0 if not valid with this rule
     */
    public int charsUsed(String expr, int index, HashMap<String, BnfRule> rules)
    {
        return charsUsed(expr, index, rules, false);
    }

     /**
     * Function which optionally skips whitespace before calling the validTokens function
     * to get the number of characters this rule can use in the input expression. If
     * validTokens returns 0, then this function returns 0 regardless of the whitespace 
     * skipped; however, if validTokens returns non-0, this this function adds whitespace
     * skipped to that value before returning it
     * 
     * @param expr The expression to evaluate
     * @param index The index to start at
     * @param rules The list of other rules to use
     * @param keepWhitespace Whether or not to avoid skipping whitespace before evaluating the rule
     * @return The number of characters used for this rule, including whitespace; 0 if not valid with this rule
     */
    public int charsUsed(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace)
    {
        int origIndex = index;
        int whitespace = 0;
        
        debug(""+index);
        
        if(!keepWhitespace)
        {
            index = skipWhitespace(expr, index);
            whitespace = index-origIndex;
            debug("Skipped " + whitespace + " whitespace");
        }
        
        int used = validTokens(expr, index, rules);
        
        return used > 0?used+whitespace:used;
    }
    
    /**
    * Convenience function to skip whitespace in a string
    *
    * @param expr The string to skip whitespace in
    * @param index The index of the string to start skipping at
    *
    * @return int The first non-whitespace index found
    */
    protected int skipWhitespace(String expr, int index)
    {
        while(index < expr.length() && (expr.charAt(index) == ' ' || expr.charAt(index) == '\t')) index++;
        return index;
    }
}
