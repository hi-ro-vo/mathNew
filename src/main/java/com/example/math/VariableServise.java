package com.example.math;

import java.util.HashMap;
import java.util.Map;

public class VariableServise {
    Map<String, Double> variables = new HashMap<>();

    public Double getValue(String variableName){
        return  variables.get(variableName);
    }

    public void SetValue(String variableName, Double value){
        variables.put(variableName, value);
    }
}
