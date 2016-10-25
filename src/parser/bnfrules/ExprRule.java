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
public class ExprRule extends BnfRule {
    
    public String name() {
        return "expr";
    }

    //<expr> -> <term> { <addop> <term> }
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        int addition = 0;
        int subindex = 0;
        int origIndex = index;
        
        BnfRule term = rules.get("term");
        BnfRule addop = rules.get("addop");
        if(term == null || addop == null) return 0;
        
        //Skip leading whitespace
        if(!keepWhitespace)
            index = skipWhitespace(expr, index);
        
        int whitespace = index-origIndex;
        
        addition = term.validTokens(expr, index+subindex, rules);
        while(addition > 0)
        {
            subindex += addition;
            int addsize=0, termsize = 0;
            addsize = addop.validTokens(expr, index+subindex, rules, true);
            if(addsize > 0)
            {
                termsize = term.validTokens(expr, index+subindex+addsize, rules);
            }
            if(termsize > 0)
                addition = addsize + termsize;
        }

        return subindex+(subindex>0?whitespace:0);
    }
    
}
