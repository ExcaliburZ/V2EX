package com.wings.v2ex.model.domain;

/**
 * Created by wing on 2016/5/22.
 */
public class Tab {
    private String name;
    private int id;

    public Tab(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
