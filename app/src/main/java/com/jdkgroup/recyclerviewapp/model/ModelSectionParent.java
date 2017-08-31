package com.jdkgroup.recyclerviewapp.model;

import java.util.List;

public class ModelSectionParent {
    private String name;
    private List<ModelSectionChild> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelSectionChild> getChilds() {
        return childs;
    }

    public void setChilds(List<ModelSectionChild> childs) {
        this.childs = childs;
    }

}