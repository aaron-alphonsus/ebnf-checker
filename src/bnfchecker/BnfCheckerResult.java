/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnfchecker;

import java.util.ArrayList;

/**
 *
 * @author 7280681
 */
public class BnfCheckerResult {
    private boolean _valid;
    private ArrayList<String> _tokens;
    public BnfCheckerResult(boolean valid, ArrayList<String> tokens)
    {
        _valid = valid;
        _tokens = tokens;
    }
    
    public ArrayList<String> tokens()
    {
        return _tokens;
    }
    
    public boolean valid()
    {
        return _valid;
    }
}
