/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author esprit
 */
public class ConditionSaisie {
     private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
       private static Matcher matcher;

     public static boolean validate(final String hex) {
         CharSequence cs = new StringBuffer(hex);
         Matcher matcher = pattern.matcher(cs);
        return matcher.matches();
    }
     public static boolean checkNumeric(String value)    {
    String number=value.replaceAll("\\s+","");
    for(int j = 0 ; j<number.length();j++){ 
            if(!(((int)number.charAt(j)>=47 && (int)number.charAt(j)<=57)))
            {
                return false;
            }
    }     
    return true;
}
 
    
}
