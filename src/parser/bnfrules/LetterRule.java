/* For package and program info, please run javadoc */
package parser.bnfrules;

import bnfchecker.BnfRule;
import java.util.HashMap;

/**
* {@link bnfchecker.BnfRule} for checking the Letter rule defined as &lt;letter&gt;  -&gt; A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z | a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z | _
*/
public class LetterRule extends BnfRule {
    
    /**
    *   Gets the name of the rule -- 'letter'
    *
    *   @return The string 'letter'
    */
    public String name() {
        return "letter";
    }

    /**
    * Parses an input expression, starting at an index, to see if the next few
    * characters fit the rule &lt;letter&gt;  -&gt; A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z | a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z | _
    *
    * @param expr The string expression to parse
    * @param index The index to start looking at
    * @param rules The list of other {@link bnfchecker.BnfRule}s in the grammer
    *
    * @return int - The number of characters used to complete the rule
    */
    /*<letter> -> A | B | C | D | E | F | G | H | I | J | K | L | M |
                  N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
                  a | b | c | d | e | f | g | h | i | j | k | l | m |
                  n | o | p | q | r | s | t | u | v | w | x | y | z | _ */
    protected int validTokens(String expr, int index, HashMap<String, BnfRule> rules) {
      
        //If first token > 1 characters, not a digit
        if (index >= expr.length()) return 0;
        //Get the character
        char candidate = expr.charAt(index);
        
        //Check if the token character is a letter or _
        if(candidate >= 'a' && candidate <= 'z' ||
           candidate >= 'A' && candidate <= 'Z' ||
           candidate == '_') return 1;
        
        return 0;
    }
    
}
