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
public class DigitRule extends BnfRule {
    
    public String name() {
        return "expr";
    }

    //<expr> -> <term> { <addop> <term> }
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
        int addition = 0;
        int subindex = 0;
        
        addition = rules.get("term").validTokens(expr, index+subindex, rules);
        while(addition > 0)
        {
            subindex += addition;
            int addsize=0, termsize = 0;
            addsize = rules.get("addop").validTokens(expr, index+subindex, rules);
            if(addsize > 0)
            {
                termsize = rules.get("term").validTokens(expr, index+subindex+addsize, rules);
            }
            if(termsize > 0)
                addition = addsize + termsize;
        }

        return subindex;
    }
    
}
