package br.senai.sp.informatica.mobile.apphearme.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historico {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("clienteId")
    @Expose
    private Integer clienteId;
    @SerializedName("data")
    @Expose
    private String dataHorarioAlerta;
    @SerializedName("latitude")
    @Expose
    private Float lat;
    @SerializedName("longitude")
    @Expose
    private Float lon;

    public int getId() {
        return this.id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getDataHorarioAlerta() {
        return dataHorarioAlerta;
    }

    public void setDataHorarioAlerta(String dataHorarioAlerta) {
        this.dataHorarioAlerta = dataHorarioAlerta;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}


