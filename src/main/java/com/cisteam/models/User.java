package com.cisteam.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    private String username;
    private String password;

    private String fullname;

    private Date date;

    public User(String username, String fullname, String password) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.date = new Date();
    }

    public User() {}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
