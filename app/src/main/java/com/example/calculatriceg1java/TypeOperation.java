package com.example.calculatriceg1java;

public enum TypeOperation {
    ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String symbole;

    TypeOperation(String symbole) {
        this.symbole=symbole;
    }

    public String getSymbole() {
        return symbole;
    }
}
