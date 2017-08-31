package com.jdkgroup.recyclerviewapp.model;

import java.util.List;

public class ModelSection2Parent {

    private String headerTitle;
    private List<ModelSection2Child> alChild;


    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }


    public List<ModelSection2Child> getAlChild() {
        return alChild;
    }

    public void setAlChild(List<ModelSection2Child> alChild) {
        this.alChild = alChild;
    }
}
