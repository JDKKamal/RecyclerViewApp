package com.jdkgroup.recyclerviewapp.model;

import java.util.ArrayList;

public class ModelMain {
    private String mTitle;
    private ArrayList<String> mArrayChildren;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public ArrayList<String> getArrayChildren() {
        return mArrayChildren;
    }

    public void setArrayChildren(ArrayList<String> arrayChildren) {
        mArrayChildren = arrayChildren;
    }
}