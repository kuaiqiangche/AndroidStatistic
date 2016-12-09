package com.cocoa.plug


public class StringUtils {

    public static String toUpperCaseFirstChat(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}