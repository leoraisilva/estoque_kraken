package br.com.kraken.estoque.java.model;

public enum Rules {
    ADMIN("admin"),
    SALES("sales"),
    BUYER("buyer");

    private String rules;
    Rules (String rules) {
        this.rules = rules;
    }

}
