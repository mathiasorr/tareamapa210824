package com.example.tareamapa210824;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.0100692, -79.4716034), 18);
        mapa.moveCamera(camUpd1);

        mapa.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }
            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.ventana_de_informacion, null);
                ImageView imageView = view.findViewById(R.id.logoRestaurante);
                TextView titleTextView = view.findViewById(R.id.txttitulo);
                TextView descriptionTextView = view.findViewById(R.id.txtdescripcion);

                titleTextView.setText(marker.getTitle());
                descriptionTextView.setText(Html.fromHtml("<b>Ubicación:</b><br/>" + marker.getPosition().latitude + ", " + marker.getPosition().longitude));

                // Aquí puedes agregar la lógica para mostrar el logo de cada restaurante
                if (marker.getTitle().equals("KFC PASEO SHOPPING")) {
                    imageView.setImageResource(R.drawable.kfc_logo);
                } else if (marker.getTitle().equals("PIZZA HUT")) {
                    imageView.setImageResource(R.drawable.pizza_hut_logo);
                } else if (marker.getTitle().equals("CARL'S JR")) {
                    imageView.setImageResource(R.drawable.carls_jr_logo);
                } else if (marker.getTitle().equals("GUS QUEVEDO")) {
                    imageView.setImageResource(R.drawable.gus_quevedo_logo);
                } else if (marker.getTitle().equals("LOKOS D' ASAR")) {
                    imageView.setImageResource(R.drawable.lokos_d_asar_logo);
                }

                return view;
            }
        });

        // Agregar marcadores para cada restaurante
        agregarMarcador(-1.0100692, -79.4716034, "KFC PASEO SHOPPING");
        agregarMarcador(-1.0099261, -79.4715790, "PIZZA HUT");
        agregarMarcador(-1.0097243, -79.4713298, "CARL'S JR");
        agregarMarcador(-1.0098779, -79.4712212, "GUS QUEVEDO");
        agregarMarcador(-1.0136201, -79.4714537, "LOKOS D' ASAR");
    }

    private void agregarMarcador(double lat, double lng, String titulo) {
        LatLng ubicacion = new LatLng(lat, lng);
        mapa.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title(titulo));
    }
}