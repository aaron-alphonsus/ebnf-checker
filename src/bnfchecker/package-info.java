/**
 * Package containing classes to check if strings are valid in a BNF grammar.
 * <p>
 * {@link bnfchecker} is the main class, create one and load it with {@link BnfRule} objects,
 * when you call the method checkExpression(), it will return a {@link BnfCheckerResult} with
 * information about what rule best matches the input string. The {@link BnfRule} objects
 * should be subclasses of the one in this package which implement validTokens() to determine
 * how many characters of the input string can be used to fulfill a rule.
 * 
 */
 
 package bnfchecker;
