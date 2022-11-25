package com.meetyourroommate.app.shared.domain.enumerate;

public enum Status {
    PENDING ("Pending"),
    FINISHED ("Finished"),
    ACCEPTED ("Aceepted"),
    DECLINED ("Declined");
    private final String name;
    Status(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
