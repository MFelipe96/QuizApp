package com.example.appquiz.util;

public class Math {

    //Checking the value if null return zero
    public int ParsIntOrDefalt(String value){
        return Integer.parseInt(NullIntegerDefalt(value));
    }

    private String NullIntegerDefalt(String value) {
        if (!isIntValue(value)) value = "0";
        return value;
    }

    private boolean isIntValue(String val){
        try {
            val=val.replace(" ","");
            Integer.parseInt(val);
        } catch (Exception e) {return false;}
        return true;
    }
}