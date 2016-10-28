/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
 *
 * @author 7280681
 */
public class FactorRule extends BnfRule {
    
    public String name() {
        return "factor";
    }

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
        
        debug("Recurse to integer");
        indInteger = integer.charsUsed(expr, index, rules);
        
        debug("Recurse to float");
        indFloat = floatrule.charsUsed(expr, index, rules);
        
        debug("Recurse to id");
        indId = id.charsUsed(expr, index, rules);
        
        if(index < expr.length()) {
            if (expr.charAt(index) == '(') {
                debug("Recurse to expr");
                indExpr = exprrule.charsUsed(expr, index+1, rules);
                if (indExpr > 0) {
                    newInd = skipWhitespace(expr, index+indExpr+1);
                    if (newInd < expr.length() && 
                        expr.charAt(newInd) == ')')
                        indExpr = newInd - index + 1;
                    else
                        indExpr = 0;
                }
            }
        }   
        
        if (index < expr.length() && expr.charAt(index) == '-') {
            debug("Recurse to factor");
            indFactor = factor.charsUsed(expr, index+1, rules, false);
            if (indFactor > 0)
                indFactor += 1;
        }
        
        return Math.max(Math.max(indInteger, indFloat), 
            Math.max(indId, 
                Math.max(indExpr, indFactor)));
    }
    
}
