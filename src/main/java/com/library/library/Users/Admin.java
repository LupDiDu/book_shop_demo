package com.library.library.Users;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{
    public Admin(String name, String email, String password) {
        super(name, email, password, "admin");
    }

}
