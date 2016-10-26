package bnfchecker;

import java.util.ArrayList;

/**
 * Object used by {@link BnfChecker} to return the following
 * information about an attempt to match a string to bnf rules
 * <ul>
 * <li> The name of the rule which matched best </li>
 * <li> The number of characters by that rule </li>
 * <li> An ArrayList of the tokens parsed from the input </li>
 * </ul>
 *
 * @author Andrew Stelter
 */
public class BnfCheckerResult {
    private int _chars;
    private String _name;
    private ArrayList<String> _tokens;
    
    /**
    * Create a new {@link BnfCheckerResult} with a specific
    * number of characters used, a specific rule name, and a token list
    *
    * @param chars The number of characters used for the best matching rule
    * @param name The name of the best matching rule
    * @param tokens A list of String tokens
    */
    public BnfCheckerResult(int chars, String name, ArrayList<String> tokens)
    {
        _chars = chars;
        _name = name;
        _tokens = tokens;
    }
    
    /**
    * Get the list of tokens contained
    *
    * @return An ArrayList&lt;String&gt; object containing a list of tokens
    */
    public ArrayList<String> tokens()
    {
        return _tokens;
    }
    
    /**
    * Get the number of characters used
    *
    * @return An int; the number of characters
    */
    public int chars()
    {
        return _chars;
    }
    
    /**
    * Get the name of the rule which matched best
    *
    * @return A String; the name of the rule
    */
    public String name()
    {
        return _name;
    }
}
