package br.senai.sp.informatica.mobile.apphearme.model;

public class Device {
    private String devName;
    private String devAddress;
    private boolean connected;

    public Device(String name, String address, String connected){
        this.devName = name;
        this.devAddress = address;
        if(connected == "true"){
            this.connected = true;
        }else{
            this.connected = false;
        }
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getAddress() {
        return devAddress;
    }

    public void setAddress(String devAddress) {
        this.devAddress = devAddress;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
