package com.testSpring.Blog.controllers.gitUtils.pojos;

import java.util.ArrayList;

public class Branches {
    public ArrayList<Branch> branches = new ArrayList<>();

    private static Branches instance;

    public static synchronized Branches getInstance() {
        if (instance == null) {
            instance = new Branches();
        }
        return instance;
    }

    public Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.name.equals(branchName))
                return branch;
        }
        Branch branch = new Branch(branchName);
        branches.add(branch);
        return branch;
    }
}
