package br.senai.sp.informatica.mobile.apphearme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alerta {
    @SerializedName("idAlerta")
    @Expose
    private Integer idAlerta;
    @SerializedName("nomeAlerta")
    @Expose
    private String nomeAlerta;
    @SerializedName("tipoAlerta")
    @Expose
    private Integer tipoAlerta;

    public Integer getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getNomeAlerta() {
        return nomeAlerta;
    }

    public void setNomeAlerta(String nomeAlerta) {
        this.nomeAlerta = nomeAlerta;
    }

    public Integer getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(Integer tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

}