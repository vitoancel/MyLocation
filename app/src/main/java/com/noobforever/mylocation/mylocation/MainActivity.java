package com.noobforever.mylocation.mylocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.noobforever.mylocation.mylocation.Utils.GeoLocation;

public class MainActivity extends AppCompatActivity {

    EditText Lat,Lon;
    Button Ubicar, Mostrar;
    GeoLocation geoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lat = (EditText)findViewById(R.id.txtLat);
        Lon = (EditText)findViewById(R.id.txtLon);
        Ubicar = (Button) findViewById(R.id.btnUbicar);
        Mostrar = (Button)findViewById(R.id.btnMostrar);

        geoLocation = new GeoLocation(this);

        Ubicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarUbicacion();
            }
        });

        Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datos = new Bundle();
                datos.putDouble("Lat", Double.parseDouble(Lat.getText().toString()));
                datos.putDouble("Lon",Double.parseDouble(Lon.getText().toString()));
                Intent newActivity = new Intent(v.getContext(), MapsActivity.class);
                newActivity.putExtras(datos);
                v.getContext().startActivity(newActivity);
            }
        });
    }
    private void MostrarUbicacion() {
        geoLocation.getTextCoordinates(Lon, Lat,null,null);

    }

    @Override
    protected void onStop() {
        super.onStop();
        geoLocation.stopLocationUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        geoLocation.stopLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        geoLocation.stopLocationUpdates();
    }
}
