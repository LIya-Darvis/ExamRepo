package com.example.exam;

public class Recept {
    String id, name, sostav, author;

    public Recept(String id, String name, String sostav, String author) {
        this.id = id;
        this.name = name;
        this.sostav = sostav;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSostav() {
        return sostav;
    }

    public void setSostav(String sostav) {
        this.sostav = sostav;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
