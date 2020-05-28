package com.chanchuan.eventbus;

public class DetailEvent {
    int id;

    public DetailEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
