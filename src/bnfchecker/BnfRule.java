package bnfchecker;

import java.util.HashMap;

/**
 * Class which checks uses recursive descent parsing
 * to see if a set of characters matches a bnf rule
 *
 * @author Andrew Stelter
 */
public abstract class BnfRule {
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
    * @param keepWhitespace Whether or not the rule should skip leading whitespace
    *
    * @return The number of characters that this rule can use, starting at index.
    *         0 if the rule can't be matched at all
    */
    public abstract int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace);
       
    /**
    * Checks how many characters of the string, starting at index,
    * can be used to match this rule. Defaults the keepWhitespace parameter
    * to false
    *
    * @param expr The String expression to evaluate
    * @param index The index of expr to start at, inclusive
    * @param rules A hash of all available rules, hashed by their names
    *
    * @return The number of characters that this rule can use, starting at index.
    *         0 if the rule can't be matched at all
    */
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules)
    {
        return validTokens(expr, index, rules, false);
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
        while(expr.charAt(index) == ' ' || expr.charAt(index) == '\t') index++;
        return index;
    }
}
