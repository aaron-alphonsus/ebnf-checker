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
    private int _chars;
    private String _name;
    private ArrayList<String> _tokens;
    public BnfCheckerResult(int chars, String name, ArrayList<String> tokens)
    {
        _chars = chars;
        _name = name;
        _tokens = tokens;
    }
    
    public ArrayList<String> tokens()
    {
        return _tokens;
    }
    
    public int chars()
    {
        return _chars;
    }
    
    public String name()
    {
        return _name;
    }
}
