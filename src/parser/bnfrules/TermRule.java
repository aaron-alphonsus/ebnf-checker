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
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        int addition = 0;
        int subindex = 0;
        
        addition = rules.get("factor").validTokens(expr, index+subindex, rules);
        while(addition > 0)
        {
            subindex += addition;
            int mulsize=0, factorsize = 0;
            mulsize = rules.get("mulop").validTokens(expr, index+subindex, rules);
            if(mulsize > 0)
            {
                factorsize = rules.get("factor").validTokens(expr, index+subindex+mulsize, rules);
            }
            if(factorsize > 0)
                addition = mulsize + factorsize;
        }

        return subindex;
    }
    
}
