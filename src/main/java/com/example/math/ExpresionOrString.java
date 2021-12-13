package com.example.math;

import com.example.math.Expresions.Expresion;

public class ExpresionOrString{
    private Expresion expresion = null;
    private String string = null;
    private Boolean isExpresion;

    public ExpresionOrString(Expresion expresion) {
        this.expresion = expresion;
        isExpresion = true;
    }

    public ExpresionOrString(String string) {
        this.string = string;
        isExpresion = false;
    }

    public Expresion getExpresion() {
        if (isExpresion) return expresion;
        return null;
    }

    public String getString() {
        if (!isExpresion) return string;
        return null;
    }

    public Integer indexOf(String command){
        if (isExpresion) return - 1;
        return string.indexOf(command);
    }

    @Override
    public String toString() {
        if (isExpresion){
            return "["+expresion.toString()+"]";
        } else {
            return string;
        }
    }
}
