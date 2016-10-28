/**
 * <h1>CSC461 - Program 2</h1>
 * <h2>Recursive Descent Parser</h2>
 * This program uses Recursive Descent Parsing to determine if
 * expressions are valid.
 * <p>
 * Recursive descent parsing is often used in compilers to determine whether the input source code 
 * is  syntactically  correct.  Recursive  descent  parsing 
 * consists  of  writing  a  subprogram,  possibly 
 * recursive,  for  each  syntactic  element  of  the  language.  An  input  token  is  parsed  by  calling  the 
 * appropriate subprogram, which decides if it is a valid language element.
 * <p>
 * The EBNF Rules used to determine validity of expressions follow:
 * <pre>
 * &lt;expr&gt;    -&gt; &lt;term&gt;   { &lt;addop&gt; &lt;term&gt; }
 * &lt;term&gt;    -&gt; &lt;factor&gt; { &lt;mulop&gt; &lt;factor&gt; }
 * &lt;factor&gt;  -&gt; &lt;integer&gt; | &lt;float&gt; | &lt;id&gt; | '('&lt;expr&gt; ')'| [-] &lt;factor&gt;
 * &lt;integer&gt; -&gt; &lt;digit&gt; { &lt;digit&gt; }
 * &lt;float&gt;   -&gt; &lt;integer&gt; . &lt;integer&gt;
 * &lt;id&gt;      -&gt; &lt;letter&gt; { &lt;letter&gt; | &lt;digit&gt; }
 * &lt;letter&gt;  -&gt; A | B | C | D | E | F | G | H | I | J | K | L | M |
 *              N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
 *              a | b | c | d | e | f | g | h | i | j | k | l | m |
 *              n | o | p | q | r | s | t | u | v | w | x | y | z | _
 * &lt;digit&gt;   -&gt; 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * &lt;addop&gt;   -&gt; + | -
 * &lt;mulop&gt;   -&gt; * | / | %
 * </pre>
 * Usage:
 * <pre>
 * ./Parser.Parser [-t][-b][-d]
 * -t - Output tokens after processing an expression
 * -b - List the best matching EBNF rule, and the number of characters matched (if not all)
 * -d - Debuggin: Give a very verbose output of how the recursion goes
 * </pre>
 * Modifications and Development Timeline: 
 * <pre>
 * Date     Description
 * -----    ---------------------------------
 *  7/10    Set up framework and enough rules for proof of concept
 *  9/10    Switched from using tokens to just using the input string
 * 11/10    Removed whitespace before some rules
 * 14/10    Fixed program exit conditions
 * 17/10    Set up token outputting
 * 21/10    Added expr and term rules
 * 23/10    Added addop rule
 *          Made skipWhitespace a method of {@link bnfchecker.BnfRule}
 * 24/10    Completed integer rule
 *          Fixed javadoc comments for netbeans
 *          Added float - buggy
 *          Fixed float
 *          Switched to charsUsed method in {@link bnfchecker.BnfRule} for all whitespace skipping
 *          Fixed a bug in float
 * 25/10    Commented the rules
 * 27/10    Added factor rule
 *          Removed infinite loops
 *          Finished getting all rules added
 *          Fixed all remaining bugs
 *
 * </pre>
 * @author Andrew Stelter
 * @author Aaron Alphonsus
 */
 package parser;
