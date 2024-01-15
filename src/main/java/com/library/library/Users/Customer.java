package com.library.library.Users;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User{

    public Customer() {
        super();
    }

    public Customer(String name, String email, String password) {
        super(name, email, password, "customer");
    }
    public String getEmail() {
        return super.getEmail();
    }
    public String toString() {
        return super.toString();
    }
}
