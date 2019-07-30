package com.gklee.regionselector;

public class RegionBean {
    public String name;
    public String id;

    public RegionBean(String s, String s1) {
        id=s;
        name=s1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
