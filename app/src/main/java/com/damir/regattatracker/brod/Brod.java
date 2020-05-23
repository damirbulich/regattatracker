package com.damir.regattatracker.brod;

import com.damir.regattatracker.helper.PostData;

public class Brod implements PostData {
    private int id;
    private String name;

    public Brod(String _name){
        this.setName(_name);
    }

    public Brod(int _id, String _name){
        this.setId(_id);
        this.setName(_name);
    }

    @Override
    public String serialiseData() {
        return "{ \"name\":\""+ this.getName() +"\"}";
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
