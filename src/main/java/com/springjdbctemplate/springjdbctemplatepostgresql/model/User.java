package com.springjdbctemplate.springjdbctemplatepostgresql.model;

public class User {


    private int id;

    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
