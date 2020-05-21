package com.testSpring.Blog.controllers.gitUtils.pojos;

import java.util.ArrayList;

public class Branch {
    public String name;
    public ArrayList<Commit> commits = new ArrayList<>();

    private static Branch instance;

    public Branch(String name) {
        this.name = name;
    }

    public Branch() {

    }

    static synchronized Branch getInstance() {
        if (instance == null) {
            instance = new Branch();
        }
        return instance;

    }

}
