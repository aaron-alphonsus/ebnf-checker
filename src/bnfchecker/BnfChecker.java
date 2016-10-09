/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnfchecker;

import java.util.HashMap;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;

/**
 *
 * @author 7280681
 */
public class BnfChecker {
    private HashMap<String, BnfRule> _rules = new HashMap<String, BnfRule>();
    
    public BnfChecker(){}
    
    public BnfCheckerResult checkExpression(String expr)
    {
        String bestMatch = "No Rule";
        int bestChars = 0;
        int charsUsed;
        ArrayList<String> tokens = tokenize(expr);
        for(String s : _rules.keySet())
        {
            charsUsed = _rules.get(s).validTokens(expr, 0, _rules);
            if(charsUsed > bestChars)
            {
                bestChars = charsUsed;
                bestMatch = s;
            }
            
            //If any rule uses all tokens, then it is valid
            if(bestChars == expr.length())
                return new BnfCheckerResult(bestChars, bestMatch, tokens);
        }
        return new BnfCheckerResult(bestChars, bestMatch, tokens);
    }
    
    public void addRule(BnfRule rule)
    {
        _rules.put(rule.name(), rule);
    }
    
    private ArrayList<String> tokenize(String expr)
    {
        return new ArrayList<String>();
    }
}
