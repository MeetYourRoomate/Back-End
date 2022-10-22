package com.meetyourroommate.app.rental.domain.enumerate;

public enum RentalStatus {
    BUSY("Busy"),
    FREE("Free");
    private final String name;
    RentalStatus (String name){
        this.name = name;
    }
}
