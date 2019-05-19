package com.example.imhere;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocWithMap extends Activity implements LocationListener {
  private GoogleMap mMap;
  private TextView latituteField;
  private TextView longitudeField;
  private LocationManager locationManager;
  private String provider;
  float lng;
  float lat;
  public static float tlat,tlng;
   


/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.locwithmap);
    latituteField = (TextView) findViewById(R.id.tvm2);
    longitudeField = (TextView) findViewById(R.id.tvm4);
    
    // Get the location manager
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    // Define the criteria how to select the locatioin provider -> use
    // default
    Criteria criteria = new Criteria();
    provider = locationManager.getBestProvider(criteria, false);
    Location location = locationManager.getLastKnownLocation(provider);

    // Initialize the location fields
    if (location != null) {
      System.out.println("Provider " + provider + " has been selected.");
      onLocationChanged(location);
    } else {
      latituteField.setText("Location not available");
      longitudeField.setText("Location not available");
    }
  }

  /* Request updates at startup */
  @Override
  protected void onResume() {
    super.onResume();
    locationManager.requestLocationUpdates(provider, 400, 1, this);
  }

  /* Remove the locationlistener updates when Activity is paused */
  @Override
  protected void onPause() {
    super.onPause();
    locationManager.removeUpdates(this);
  }

  @Override
  public void onLocationChanged(Location location) {
    lat = (float) (location.getLatitude());
    lng = (float) (location.getLongitude());
    
   
   setData(lat,lng);
    
   
    latituteField.setText(String.valueOf(lat));
    longitudeField.setText(String.valueOf(lng));
    
    mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.fmap)).getMap();
    mMap.addMarker(new MarkerOptions()
            .position(new LatLng(lat,lng))
            .title("I'm here"));
    
    }
  
  public static void setData(float lat,float lng)
  {
	  tlat=lat;
	  tlng=lng;
  }
  
  public static float getLat()
  {
	  return tlat;
  }
  
  public static float getLng()
  {
	  return tlng;
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onProviderEnabled(String provider) {
    Toast.makeText(this, "Enabled new provider " + provider,
        Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onProviderDisabled(String provider) {
    Toast.makeText(this, "Disabled provider " + provider,
        Toast.LENGTH_SHORT).show();
  }
} 