package com.jdkgroup.recyclerviewapp.model;

/**
 * Created by kamlesh on 8/30/2017.
 */

public class ModelAppEditText
{
    private int id;
    private String username;

    public ModelAppEditText(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
