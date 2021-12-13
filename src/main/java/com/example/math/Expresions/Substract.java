package com.example.math.Expresions;

import java.util.ArrayList;
import java.util.List;

public class Substract implements Expresion{
    private List<Expresion> childs;

    Substract(Expresion leftChild, Expresion rightChild){
        childs = new ArrayList<>();
        if (Variable.class.isInstance(leftChild)&&leftChild.getName().equals("")){
            childs.add(0, new Constant(0.0));
        } else {
            childs.add(0, leftChild);
        }
        childs.add(1, rightChild);
    }

    Substract(List<Expresion> expresionList){
        this(expresionList.get(0), expresionList.get(1));
        if (expresionList.size()!=2){
            //TODO: trow
        }
    }

    @Override
    public Double execute() {
        return childs.get(0).execute()-childs.get(1).execute();
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
        return childs.get(0).toString()+"-"+childs.get(1).toString();
    }
}
