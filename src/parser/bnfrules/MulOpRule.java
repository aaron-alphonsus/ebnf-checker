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
public class MulOpRule extends BnfRule {
    
    public String name() {
        return "mulop";
    }

    //<mulop> -> * | / | %
    public int validTokens(String expr, int index, HashMap<String, BnfRule> rules, boolean keepWhitespace) {
        int origIndex = index;
        
        //Skip leading whitespace
        if(!keepWhitespace)
            index = skipWhitespace(expr, index);
        
        int whitespace = index-origIndex;
        
        if(index >= expr.length()) return 0;
        if(expr.charAt(index) == '*' ||
           expr.charAt(index) == '/' ||
           expr.charAt(index) == '%') return 1+whitespace;
        
        return 0;
    }
    
}
