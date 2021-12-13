package com.example.math.Expresions;

import java.util.List;

public class Constant implements Expresion{
    Double number;
    public Constant(Double number){
        this.number = number;
    }
    
    @Override
    public Double execute() {
        return number;
    }

    @Override
    public List<Expresion> getChilds() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "Const{"+number+"}";
    }
}
