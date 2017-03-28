package com.magmatart.calculator;

import java.security.PrivilegedAction;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by 20311Leesoomin on 2017-03-28.
 */

public class Formula {
    private String formula;
    private Stack<Token> infixTokens;
    private Stack<Token> postfixTokens;

    Formula(String formula){
        this.formula = formula;
        this.infixTokens = new Stack<>();
        this.postfixTokens = new Stack<>();

        Stack<Token> tokens = new Stack<>();
        String store = "";

        while(formula.length()>0){
            String currentChar = formula.substring(0,1);

            if(Pattern.matches("[0-9\\.]", currentChar)){
                store += currentChar;
            }else if(Operator.getOpType(currentChar) != null){
                if(!store.isEmpty()){
                    tokens.push(new Operator.Numeric(store));
                    store = "";
                }
                tokens.push(new Operator(Operator.getOpType(currentChar)));
            }else if(Operator.Parenthesis.getParenthesisType(currentChar) != null){
                if(!store.isEmpty()){
                    tokens.push(new Operator.Numeric(store));
                    store = "";
                }
                tokens.push(new Operator.Parenthesis(Operator.Parenthesis.getParenthesisType(currentChar)));
            }else{
                if( !(currentChar.isEmpty() && !(store.isEmpty() && Pattern.matches("[0-9\\.]", formula.substring(1,1))))){
                    System.out.println("Invalid Character in formula : " + currentChar);
                }
            }
            formula = formula.substring(1);
        }

        if(!store.isEmpty())
            tokens.push(new Operator.Numeric(store));

        Stack<Token> reversedStack = new Stack<>();
        while(tokens.size()>0)
            reversedStack.push(tokens.pop());

        infixTokens = reversedStack;

        Stack<Token> infixTokens = new Stack<>();

        Stack<Token> output = new Stack<>();
        Stack<Token> operators = new Stack<>();

        while(true){

        }
    }

}
