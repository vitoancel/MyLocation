package com.noobforever.mylocation.mylocation;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double Lat, Lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = this.getIntent();
        Lat=intent.getExtras().getDouble("Lat");
        Lon=intent.getExtras().getDouble("Lon");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng rest = new LatLng(Lat, Lon);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(rest)
                .title("Aqui Estas!!!!")
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mark_icon)));

        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 4000, null);

        CameraPosition camPos = new CameraPosition.Builder()
                .target(rest)   //Centramos el mapa
                .zoom(17)         //Establecemos el zoom en 16
                .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
                .bearing(0)
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);



        mMap.animateCamera(camUpd3);

        Marker m =mMap.addMarker(markerOptions);

    }
}
