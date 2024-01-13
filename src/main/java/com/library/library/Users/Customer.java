package com.library.library.Users;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User{


    public Customer(String name, String email, String password) {
        super(name, email, password, "customer");
    }

}
