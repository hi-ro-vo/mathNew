package com.example.math.Expresions;

import java.util.LinkedList;
import java.util.List;

public class Exponent implements Expresion{
    private List<Expresion> childs;

    Exponent(Expresion leftChild, Expresion rightChild){
        childs = new LinkedList<>();
        childs.add(0, leftChild);
        childs.add(1, rightChild);
    }

    Exponent(List<Expresion> expresionList){
        this(expresionList.get(0), expresionList.get(1));
        if (expresionList.size()!=2){
            //TODO: trow
        }
    }

    @Override
    public Double execute() {
        return Math.pow(childs.get(0).execute(),childs.get(1).execute());
    }

    @Override
    public List<Expresion> getChilds() {
        return childs;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return childs.get(0).toString()+"^"+childs.get(1).toString();
    }
}
