package com.damir.regattatracker;

public class Brod implements PostData{
    private int id;
    private String naziv;

    public Brod(int _id, String _naziv){
        this.setId(_id);
        this.setNaziv(_naziv);
    }

    @Override
    public String serialiseData() {
        return "{ \"naziv\":\""+ this.getNaziv() +"\"}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
