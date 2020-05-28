package com.chanchuan.eventbus;

public class CartEvent {
    int price;
    int number;

    public CartEvent(int price, int number) {
        this.price = price;
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
