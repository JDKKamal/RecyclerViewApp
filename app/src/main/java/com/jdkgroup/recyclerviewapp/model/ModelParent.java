package com.jdkgroup.recyclerviewapp.model;

import java.util.ArrayList;
import java.util.List;

public class ModelParent
{
    String pid, pname, parentid;
    List<ModelChild> al_child = new ArrayList<>();

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getParentid()
    {
        return parentid;
    }

    public void setParentid(String parentid)
    {
        this.parentid = parentid;
    }

    public List<ModelChild> getAl_child()
    {
        return al_child;
    }

    public void setAl_child(List<ModelChild> al_child)
    {
        this.al_child = al_child;
    }
    
    
}
