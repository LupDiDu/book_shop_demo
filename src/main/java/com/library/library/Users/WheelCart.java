package com.library.library.Users;

import com.library.library.Books.Book;

import java.util.ArrayList;

public class WheelCart {
    private ArrayList<Book> cart;

    public ArrayList<Book> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Book> cart) {
        this.cart = cart;
    }

    public WheelCart(ArrayList<Book> cart) {
        this.cart = cart;
    }
}
