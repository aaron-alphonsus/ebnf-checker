/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.bnfrules;
import bnfchecker.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 7280681
 */
public class MulOpRule extends BnfRule {
    
    public String name() {
        return "mulop";
    }

    public int validTokens(ArrayList<String> tokens, int index, HashMap<String, BnfRule> rules) {
        if(index >= tokens.size() || tokens.get(index).equals("*")) return 1;
        return 0;
    }
    
}
