package com.example.math.Expresions;



import com.example.math.VariableServise;

import java.util.List;

public class Variable implements Expresion{
    private String name;
    private VariableServise variableServise;

    public Variable(String name, VariableServise variableServise) {
        this.name = name;
        this.variableServise = variableServise;
    }

    @Override
    public Double execute() {
        return variableServise.getValue(name);
    }

    @Override
    public List<Expresion> getChilds() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "["+name+"]";
    }
}
