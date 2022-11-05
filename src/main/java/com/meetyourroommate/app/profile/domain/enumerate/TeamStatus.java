package com.meetyourroommate.app.profile.domain.enumerate;

public enum TeamStatus {

    ONTEAM("OnTeam"),
    WITHOUTTEAM("WithoutTeam");
    private final String name;
    TeamStatus (String name) {
        this.name = name;
    }
}
