package com.example.math.Expresions;

import java.util.List;

public class VariableAssignment implements Expresion{
    Expresion expresion;
    String name;

    public VariableAssignment(Expresion expresion, String name) {
        this.expresion = expresion;
        this.name = name;
    }

    @Override
    public Double execute() {
        Double res = expresion.execute();
        ExpresionFactory.variableServise.SetValue(name, res);
        return res;
    }

    @Override
    public List<Expresion> getChilds() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
