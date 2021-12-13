package com.example.math;

import com.example.math.Expresions.Expresion;
import com.example.math.Expresions.ExpresionFactory;
import com.example.math.Expresions.VariableAssignment;
import org.springframework.stereotype.Service;


import java.util.Arrays;

@Service
public class ParserImpl implements Parser {


    @Override
    public Expresion parse(String str) {
        String name = "";
        if (str.indexOf("=")!=-1){
            name = str.substring(0, str.indexOf("=")).trim();
            str = str.substring(str.indexOf("=")+1);
        }

        StringTree stringTree = new StringTree(str.replaceAll("\\s",""), null);
        Expresion expresion = createExpresionsTree(stringTree);

        if (!name.equals("")){
            return new VariableAssignment(expresion, name);
        }
        return expresion;
    }

    private Expresion createExpresionsTree(StringTree tree) {
        Integer operationRate = tree.getNumber();
        tree.getListOfSubStrings().forEach(subTree -> {
            if (subTree.getNumber() == 1){
                subTree.setExpresion(parseString(new StringWithExpressions(subTree)));
            } else {
                if (subTree.getNumber() != operationRate){
                    subTree.setExpresion(createExpresionsTree(subTree));
                }
            }
        });
        return parseString(new StringWithExpressions(tree));
    }

    private Expresion parseString(StringWithExpressions str) {
        String command = "";
        Pair position = null;
        for (String c : ExpresionFactory.getListOfPriority()) {
            Pair i = str.indexOf(c);
            if (i != null) {
                position = i;
                command = c;
                break;
            }
        }
        if (position != null) {
            System.out.println(command);
            return ExpresionFactory.getExpresion(command, Arrays.asList(parseString(str.substring(new Pair(0, 0), position)),
                    parseString(str.substring(new Pair(position.getStart(), position.getEnd() + 1)))));
        } else {
            if (str.getExpresionOrString().getExpresion()!=null){
                return str.getExpresionOrString().getExpresion();
            }
            return ExpresionFactory.getExpresion(str.getExpresionOrString().getString(), null);
        }

    }

    ;

    public String test(String str) {
        str = "2+assd2*2";
        ExpresionFactory.variableServise.SetValue("assd2", 12.0);
        Expresion expresion = createExpresionsTree(new StringTree(str, null));
        return expresion.execute().toString();
    }


}
