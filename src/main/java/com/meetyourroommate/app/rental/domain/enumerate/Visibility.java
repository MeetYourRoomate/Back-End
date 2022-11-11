package com.meetyourroommate.app.rental.domain.enumerate;

public enum Visibility {
    VISIBLE ("Visible"),
    NOTVISIBLE ("NotVisible");
    private final String name;
    Visibility(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
