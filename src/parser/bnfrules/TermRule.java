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
public class TermRule extends BnfRule {
    
    public String name() {
        return "term";
    }
    
    //<term> -> <factor> { <mulop> <factor> }
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        int addition = 0;
        int subindex = 0;
        
        BnfRule factor = rules.get("factor");
        BnfRule mulop = rules.get("mulop");
        
        if(factor == null || mulop == null) return 0;
        
        addition = factor.charsUsed(expr, index+subindex, rules);
        while(addition > 0)
        {
            subindex += addition;
            int mulsize=0, factorsize = 0;
            mulsize = mulop.charsUsed(expr, index+subindex, rules, true);
            if(mulsize > 0)
            {
                factorsize = factor.charsUsed(expr, index+subindex+mulsize, rules);
            }
            if(factorsize > 0)
                addition = mulsize + factorsize;
        }

        return subindex;
    }
    
}
