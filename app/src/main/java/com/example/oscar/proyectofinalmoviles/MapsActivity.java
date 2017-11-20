package com.example.oscar.proyectofinalmoviles;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    double lat1,lat2,lat3,lat4,lat5,lat6,lat7,lat8,lat9,lat10,lat11,lat12,lat13,lat14,lat15,lat16,lat17,lat18,lat19;
    double longi,longi2,longi3,longi4,longi5,longi6,longi7,longi8,longi9,longi10,longi11,longi12,longi13,longi14,longi15,longi16,longi17,longi18,longi19;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lat1=-76.199862;lat2=-76.199161;
        lat3=-76.200786;lat4=-76.201194;
        lat5=-76.19922;lat6=-76.200072;
        lat7=-76.199645;lat8=-76.200669;
        lat9=-76.202523;lat10=-76.204434;
        lat11=-76.203147;lat12=-76.202116;
        lat13=-76.201409;lat14=-76.204524;
        lat15=-76.204181;lat16=-76.199479;
        lat17=-76.199647;lat18=-76.201665;
        lat19=-76.201447;
        longi=4.062779;longi2=4.063773;
        longi3=4.063457;longi4=4.064534;
        longi5=4.062952;longi6=4.063095;
        longi7=4.06384;longi8=4.064312;
        longi9=4.065465;longi10=4.066357;
        longi11=4.065765;longi12=4.065252;
        longi13=4.064896;longi14=4.065781;
        longi15=4.066315;longi16=4.06261;
        longi17=4.062846;longi18=4.065111;
        longi19=4.063804;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        miUbicacion();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });

        LatLng AudiFaca = new LatLng(longi,lat1);
        LatLng AudiDere = new LatLng(longi2,lat2);
        LatLng Biblio = new LatLng(longi3,lat3);
        LatLng Bienestar = new LatLng(longi4,lat4);
        LatLng BloqA = new LatLng(longi5,lat5);
        LatLng BloqC = new LatLng(longi6,lat6);
        LatLng BloqD = new LatLng(longi7,lat7);
        LatLng BloqE = new LatLng(longi8,lat8);
        LatLng BloqF = new LatLng(longi9,lat9);
        LatLng BloqG = new LatLng(longi10,lat10);
        LatLng BloqH = new LatLng(longi11,lat11);
        LatLng cau = new LatLng(longi12,lat12);
        LatLng CenCul = new LatLng(longi13,lat13);
        LatLng CenDepor = new LatLng(longi14,lat14);
        LatLng Coli = new LatLng(longi15,lat15);
        LatLng Conta = new LatLng(longi16,lat16);
        LatLng Distan = new LatLng(longi17,lat17);
        LatLng Labo = new LatLng(longi18,lat18);
        LatLng Lag = new LatLng(longi19,lat19);



        mMap.addMarker(new MarkerOptions().position(AudiFaca).title("Auditorio Facaec"));
        mMap.addMarker(new MarkerOptions().position(AudiDere).title("Auditorio de Derecho"));
        mMap.addMarker(new MarkerOptions().position(Biblio).title("Biblioteca"));
        mMap.addMarker(new MarkerOptions().position(Bienestar).title("Bienestar Universitario"));
        mMap.addMarker(new MarkerOptions().position(BloqA).title("Bloque A"));
        mMap.addMarker(new MarkerOptions().position(BloqC).title("Bloque C"));
        mMap.addMarker(new MarkerOptions().position(BloqD).title("Bloque D"));
        mMap.addMarker(new MarkerOptions().position(BloqE).title("Bloque E"));
        mMap.addMarker(new MarkerOptions().position(BloqF).title("Bloque F"));
        mMap.addMarker(new MarkerOptions().position(BloqG).title("Bloque G"));
        mMap.addMarker(new MarkerOptions().position(BloqH).title("Bloque H"));
        mMap.addMarker(new MarkerOptions().position(cau).title("CAU"));
        mMap.addMarker(new MarkerOptions().position(CenCul).title("CENTRO CULTURAL"));
        mMap.addMarker(new MarkerOptions().position(CenDepor).title("CENTRO DEPORTIVO"));
        mMap.addMarker(new MarkerOptions().position(Coli).title("COLISEO"));
        mMap.addMarker(new MarkerOptions().position(Conta).title("CONTADURIA"));
        mMap.addMarker(new MarkerOptions().position(Distan).title("DISTANCIA"));
        mMap.addMarker(new MarkerOptions().position(Labo).title("LABORATORIOS"));
        mMap.addMarker(new MarkerOptions().position(Lag).title("LAGO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BloqA));
        CameraUpdate miUbicacion2 = CameraUpdateFactory.newLatLngZoom(BloqA, 18);
        mMap.animateCamera(miUbicacion2);


    }


    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 18);
       if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi ubicacion"));
        mMap.animateCamera(miUbicacion);


    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);

        }
    }

    LocationListener LocListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,LocListener);
    }

    public void recibirCoordenadas(double latRec , double lngRec,String nombre){
        LatLng coordenadas2 = new LatLng(latRec, lngRec);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.addMarker(new MarkerOptions().position(coordenadas2).title(nombre));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas2));
    }
}
