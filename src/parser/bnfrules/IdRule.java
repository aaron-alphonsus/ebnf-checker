/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 7280681
 */
public class IdRule extends BnfRule {
    
    public String name() {
        return "id";
    }

    public int validTokens(ArrayList<String> tokens, int index, HashMap<String, BnfRule> rules) {
        int subIndex = 0;
        int addition = 0;
        addition = rules.get("letter").validTokens(tokens, index, rules);
        while(addition > 0)
        {
            subIndex += addition;
            addition = Math.max(rules.get("letter").validTokens(tokens, index+subIndex, rules),
                                rules.get("digit").validTokens(tokens, index+subIndex, rules));
        }
        return subIndex;
    }
    
}
