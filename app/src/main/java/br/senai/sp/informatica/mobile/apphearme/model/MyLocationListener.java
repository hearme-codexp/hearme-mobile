package br.senai.sp.informatica.mobile.apphearme.model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MyLocationListener implements LocationListener{
    Context ctx;

    @Override
    public void onLocationChanged(Location loc){
        //editLocation.setText("");
        //pb.setVisibility(View.INVISIBLE);
        Toast.makeText(ctx, "Location changed: Lat: " + loc.getLatitude() +
        " Lng: " +  loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLongitude();
        Log.v(TAG, latitude);

        String cityName = null;
        Geocoder gcd = new Geocoder(ctx, Locale.getDefault());
        List<Address> addresses;
        try{
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if(addresses.size() > 0){
                String msg = addresses.get(0).getLocality();
                Toast.makeText(ctx, msg , Toast.LENGTH_SHORT).show();
                cityName = addresses.get(0).getLocality();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String s = longitude + "\n" + latitude + "\nMy current city is: " + cityName;
        Toast.makeText(ctx, s , Toast.LENGTH_SHORT).show();
        //editLocation.setText(s);
    }
    @Override
    public void onProviderDisabled(String provider){}

    @Override
    public void onProviderEnabled(String provider){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}

}
