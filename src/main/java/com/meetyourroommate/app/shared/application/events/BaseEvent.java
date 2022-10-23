package com.meetyourroommate.app.shared.application.events;


public class BaseEvent<T> {

    public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
