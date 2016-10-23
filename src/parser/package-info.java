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
 * &ltexpr&gt    -&gt &ltterm&gt   { &ltaddop&gt &ltterm&gt }
 * &ltterm&gt    -&gt &ltfactor&gt { &ltmulop&gt &ltfactor&gt }
 * &ltfactor&gt  -&gt &ltinteger&gt | &ltfloat&gt | &ltid&gt | '('&ltexpr&gt ')'| [-] &ltfactor&gt
 * &ltinteger&gt -&gt &ltdigit&gt { &ltdigit&gt }
 * &ltfloat&gt   -&gt &ltinteger&gt . &ltinteger&gt
 * &ltid&gt      -&gt &ltletter&gt { &ltletter&gt | &ltdigit&gt }
 * &ltletter&gt  -&gt A | B | C | D | E | F | G | H | I | J | K | L | M |
 *              N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
 *              a | b | c | d | e | f | g | h | i | j | k | l | m |
 *              n | o | p | q | r | s | t | u | v | w | x | y | z | _
 * &ltdigit&gt   -&gt 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * &ltaddop&gt   -&gt + | -
 * &ltmulop&gt   -&gt * | / | %
 * </pre>
 * Usage:
 * <pre>
 * ./Parser.Parser [-t][-b]
 * -t - Output tokens after processing an expression
 * -b - List the best matching EBNF rule, and the number of characters matched (if not all)
 * </pre>
 * Modifications and Development Timeline: 
 *
 * @author Andrew Stelter
 * @author Aaron Alphonsus
 */
 package parser;
