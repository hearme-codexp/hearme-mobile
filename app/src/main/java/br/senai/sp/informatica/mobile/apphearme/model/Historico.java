package br.senai.sp.informatica.mobile.apphearme.model;

import java.util.Date;

public class Historico {
    private long id;
    private String data;
    //private Date data;

    public Historico(long id, String data){
        this.id = id;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
