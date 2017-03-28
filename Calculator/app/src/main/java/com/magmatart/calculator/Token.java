package com.magmatart.calculator;

import android.icu.math.BigDecimal;
import android.preference.Preference;

/**
 * Created by 20311Leesoomin on 2017-03-28.
 */

enum tokenTypes{
    Operator,
    Number,
    variable,
    parenthesis
}

enum associativenesses{
    Left,
    Right
}

enum operatorTypes{
    Plus, Minus, Multiply, Divide ,Square
}

enum parenthesisTypes{
    Open, Close
}

public class Token {}

class Operator extends Token{
    public operatorTypes opType;
    public associativenesses asso;
    public int Precedence;

    public Operator(operatorTypes operatorType) {
        opType = operatorType;

        switch (this.opType) {
            case Square:
                Precedence = 3;
                break;
            case Multiply:
            case Divide:
                Precedence = 2;
                break;
            case Plus:
            case Minus:
                Precedence = 1;
                break;
            default:
        }

        switch (this.opType) {
            case Plus:
            case Minus:
            case Multiply:
            case Divide:
            case Square:
                asso = associativenesses.Left;
                break;
            default:
                asso = associativenesses.Right;
        }
    }

    //Return Operator to String : Override toString()
    public String toString() {
        switch (opType) {
            case Plus:
                return "+";
            case Minus:
                return "-";
            case Multiply:
                return "*";
            case Divide:
                return "/";
            case Square:
                return "^";
            default:
                return null;
        }
    }

    public static operatorTypes getOpType(String operatorValue){
        switch(operatorValue){
            case "+":
                return operatorTypes.Plus;
            case "-":
                return operatorTypes.Minus;
            case "*":
                return operatorTypes.Multiply;
            case "/":
                return operatorTypes.Divide;
            case "^":
                return operatorTypes.Square;
            default:
                return null;
        }
    }

    public static class Parenthesis extends Token{
        public parenthesisTypes parenthesisType;

        public Parenthesis(parenthesisTypes parenthesisType){
            this.parenthesisType = parenthesisType;
            //What is this?
            System.out.print(parenthesisType.Open);
        }

        @Override
        public String toString(){
            if(parenthesisType == parenthesisTypes.Open)
                return "(";
            else
                return ")";
        }

        public static parenthesisTypes getParenthesisType(String parenthesisValue) {
            switch (parenthesisValue) {
                case "(":
                    return parenthesisTypes.Open;
                case ")":
                    return parenthesisTypes.Close;
                default:
                    return null;
            }
        }
    }

    public static class Numeric extends Token{
        //BigDecimal 사용법
        public java.math.BigDecimal Value;

        public Numeric(String value){
            Value = new java.math.BigDecimal(value);
        }

        public Numeric(java.math.BigDecimal value){
            this.Value = value;
        }
    }
}