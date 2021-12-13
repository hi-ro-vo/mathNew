package com.example.math;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StringWithExpressions {
    private List<ExpresionOrString> list = new LinkedList<>();

    public StringWithExpressions(StringTree stringTree) {
        if (stringTree.getListOfSubStrings().isEmpty()){
            list.add(new ExpresionOrString(stringTree.getStr()));
            return;
        }
        stringTree.getListOfSubStrings().forEach(subTree ->{
            if (subTree.getExpresion() == null){
                list.add(new ExpresionOrString(subTree.getStr()));
            } else {
                list.add(new ExpresionOrString(subTree.getExpresion()));
            }
        });
    }

    private StringWithExpressions(List<ExpresionOrString> list) {
        this.list = list;
    }

    public Pair indexOf(String command){
        for (ExpresionOrString EoS: list) {
            if (EoS.indexOf(command)!=-1) return new Pair(list.indexOf(EoS), EoS.indexOf(command));
        }
        return null;
    }

    public StringWithExpressions substring(Pair start){
        return substring(start, new Pair(list.size() - 1, (list.get(list.size()-1).getExpresion()==null)?list.get(list.size()-1).getString().length():0));
    }

    public StringWithExpressions substring(Pair start, Pair stop){
        System.out.print(start);
        System.out.println(stop);
        System.out.println(toString());
        List<ExpresionOrString> res;
        if (start.getStart()==stop.getStart()){
            res = new LinkedList<>(list.subList(start.getStart(), start.getStart()+1));
            if (res.get(0).getExpresion()==null){
                res.set(0, new ExpresionOrString(res.get(0).getString().substring(start.getEnd(), stop.getEnd())));
            }
            return new StringWithExpressions(res);
        }
        res = new LinkedList<>(list.subList(start.getStart(), stop.getStart()+1));
        if (res.get(0).getExpresion() == null){
            if (res.get(0).getString().length()<=start.getEnd()){
                res.remove(0);
            } else {
                res.set(0, new ExpresionOrString(res.get(0).getString().substring(start.getEnd())));
            }
        }
        if (res.get(res.size() - 1).getExpresion() == null){
//            if (res.get(res.size() - 1 ).getString().length())
            res.set(res.size() - 1, new ExpresionOrString(res.get(res.size() - 1).getString().substring(0, stop.getEnd())));
        }
        return new StringWithExpressions(res);
    }

    public ExpresionOrString getExpresionOrString(){
        //TODO:trow если лист содержит не один элемент
        return list.get(0);
    }

    @Override
    public String toString() {
        return "StringWithExpressions{" +
                list.stream().map(expresionOrString -> expresionOrString.toString()).collect(Collectors.joining()) +
                '}';
    }
}
