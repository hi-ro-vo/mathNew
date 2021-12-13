package com.example.math;

import com.example.math.Expresions.Expresion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringTree{
    private Expresion expresion = null;
    private List<StringTree> listOfSubStrings = new ArrayList<>();
    private String str;
    private Pair indexes;
    private Integer number;

    void addSubString(StringTree stringTree){
        if (!stringTree.str.equals("")){
            listOfSubStrings.add(stringTree);
        }
    }

    StringTree(String str, Pair pair){
        this.str = str;
        this.indexes = pair;
        this.number = 1;
        Integer i = 0;
        while (i<str.length()){
            Pair ind = findPodviragenia(str, i);
            if (ind == null){
                i = str.length();
                if (!listOfSubStrings.isEmpty()){
                    Integer start = listOfSubStrings.get(listOfSubStrings.size()-1).indexes.getEnd()+1;
                    addSubString(new StringTree(str.substring(start), null));
                }

            } else {
                i = ind.getEnd()+1;
                if (ind.getStart()!=0){
                    Integer start = (listOfSubStrings.size()==0)?0:listOfSubStrings.get(listOfSubStrings.size()-1).indexes.getEnd()+1;
                    addSubString(new StringTree(str.substring(start, ind.getStart()), null));
                }
                addSubString(new StringTree(str.substring(ind.getStart()+1, ind.getEnd()), ind));
            }
        }
        if (listOfSubStrings.isEmpty()){
            number = 1;
        } else {
            number = listOfSubStrings.stream().map(stringTree -> stringTree.number).max(Integer::compareTo).get() + 1;
            listOfSubStrings.stream().forEach(stringTree -> stringTree.number=(stringTree.indexes==null)?number:stringTree.number);
        }
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Integer getNumber() {
        return number;
    }

    public List<StringTree> getListOfSubStrings() {
        return listOfSubStrings;
    }

    public String getStr() {
        return str;
    }

    public Pair getIndexes() {
        return indexes;
    }

    private Pair findPodviragenia(String str, Integer startIndex){
        Stack<Integer> stack = new Stack<>();
        for (int i=startIndex; i< str.length(); i++){
            if (str.charAt(i) == '('){
                stack.push(i);
            } else {
                if (str.charAt(i) == ')'){
                    if (stack.empty()){
                        //TODO:throw
                        return null;
                    }
                    Integer start = stack.pop();
                    if (stack.empty()){
                        return new Pair(start, i);
                    }
                }
            }
        }
        if (stack.empty()){
            return null;
        } else {
            return null;
            //TODO:throw
        }
    }
}
