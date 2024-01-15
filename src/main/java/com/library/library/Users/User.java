package com.library.library.Users;

import com.library.library.Books.Book;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Entity
@Table(name = "allUsers")
public abstract class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;

    protected User() {
    }

    public String getPassword() {
        return password;
    }

    public void setHashPassword(int hashPassword) {
        this.hashPassword = hashPassword;
    }

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int hashPassword;
    private String role;

    private void WheelCart(ArrayList<Book> cart){};

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getHashPassword() {
        return hashPassword;
    }

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.hashPassword = this.password.hashCode();
        this.role = role;
    }


    public Long getId() {
        return id;
    }
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


//    public String getPassword() {
//        return password;
//    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", hashPassword=" + hashPassword +
                ", role='" + role + '\'' +
                '}';
    }
}
