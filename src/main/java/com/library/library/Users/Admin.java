package com.library.library.Users;

import com.library.library.Books.Book;
import com.library.library.Books.BookService;
import com.library.library.Repository.BookRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin(String name, String email, String password) {
        super(name, email, password, "admin");
    }

}
