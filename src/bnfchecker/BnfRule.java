/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnfchecker;

import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * Class which checks uses recursive descent parsing
 * to see if a set of string tokens is a valid bnf sentence
 */
public abstract class BnfRule {
    /**
    *
    * Return the name of the rule to be used by other
    * rules to recurse to this rule
    */
    public abstract String name();
    
    /**
    *
    * Checks if the tokens are a valid sentence of this rule
    * If so, returns how many tokens are used to do this rule
    * Otherwise returns 0
    */
    public abstract int validTokens(ArrayList<String> tokens, int index, HashMap<String, BnfRule> rules);
}
