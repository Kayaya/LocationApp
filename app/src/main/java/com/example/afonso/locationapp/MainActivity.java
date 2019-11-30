package com.example.afonso.locationapp;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.Toast;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends Activity implements LocationListener {

    private LocationManager locationManager;
    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Download OSM maps*/
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mapView = (MapView)findViewById(R.id.mapView);

        mapView.getController().setZoom(15);
        mapView.setBuiltInZoomControls(true);
        mapView.getController().setCenter(new GeoPoint(50.9319, -1.4011));

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
       //     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        catch (SecurityException se){

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Location=" +
                location.getLatitude() + " " +
                location.getLongitude(), Toast.LENGTH_LONG).show();
        mapView.getController().setCenter(new GeoPoint(location.getLatitude(), location.getLongitude()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(this, "Status changed: " + status, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Provider" + provider + "enabled", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Provider" + provider + "disabled", Toast.LENGTH_LONG).show();

    }
}
