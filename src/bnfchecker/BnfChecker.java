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
        int tokensUsed;
        ArrayList<String> tokens = tokenize(expr);
        for(String s : _rules.keySet())
        {
            tokensUsed = _rules.get(s).validTokens(tokens, 0, _rules);
            System.out.println("Used " + tokensUsed + " tokens for rule " + s);
            //If any rule uses all tokens, then it is valid
            if(tokensUsed == tokens.size())
                return new BnfCheckerResult(true, tokens);
        }
        return new BnfCheckerResult(false, tokens);
    }
    
    public void addRule(BnfRule rule)
    {
        _rules.put(rule.name(), rule);
    }
    
    private ArrayList<String> tokenize(String expr)
    {
        ArrayList<String> tokens = new ArrayList<String>();
        for(Character c : expr.toCharArray())
        {
            if(c != ' ')
                tokens.add(c.toString());
        }
        return tokens;
    }
}
