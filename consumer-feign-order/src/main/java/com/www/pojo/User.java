package com.www.pojo;

import java.util.Date;

public class User {
    long id;
    Date date;

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User(long id) {
        this.id = id;
        this.date = new Date();
    }

    public User() {
    }
}
