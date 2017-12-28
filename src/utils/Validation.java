/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author azzenovic
 */
public class Validation {
    public static boolean test_email(String email){
    if (email.matches("[a-zA-Z1-9_.]+@[a-zA-Z1-9_.]+.[a-zA-Z1-9_.]+")){
    return false ;
    }
    else 
    { return true ;}
    
    }
    
    public static boolean isEmpty(String str){     
        return str == null || "".equals(str);
    }
    
    public static boolean isIntConvertible(String str){
        return str.matches("[0-9]+");
    }
    
    public static boolean containsAlphabetOnly(String str){
        return str.matches("[a-zA-Z ]+");
    }
    
}
