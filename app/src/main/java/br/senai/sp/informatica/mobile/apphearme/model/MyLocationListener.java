package br.senai.sp.informatica.mobile.apphearme.model;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import static android.content.ContentValues.TAG;

public class MyLocationListener implements LocationListener{
    Context ctx;
    Location loc;

    public MyLocationListener(Location initialLocation) {
        this.loc = initialLocation;
    }

    @Override
    public void onLocationChanged(Location loc){
        this.loc = loc;
        Toast.makeText(ctx, "Location changed: Lat: " + loc.getLatitude() +
        " Lng: " +  loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLongitude();
        Log.v(TAG, latitude);
    }
    @Override
    public void onProviderDisabled(String provider){}

    @Override
    public void onProviderEnabled(String provider){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}

    public Location getLoc() {
        return loc;
    }
}
