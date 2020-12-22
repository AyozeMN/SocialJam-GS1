package gs1.proyecto;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private String tipo;
    private List<Marcador> listaMarcadores;


    // Unique code to check if the location has been requested.
    private final int ACCESS_LOCATION_REQUEST_CODE = 10001;
    public FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // gets current user location fix on the device
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Obtenemos el tipo segun el boton clickado anteriormente
        tipo = getIntent().getStringExtra("type");

        BaseDeDatos baseDeDatos = new BaseDeDatos(MapsActivity.this);
        //Obtenemos toda la lista de los registros de la tabla tipo
        listaMarcadores = baseDeDatos.getMarcadores(tipo);
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

        //Permitir zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
        zoomToUserLocation();

        for (Marcador m : listaMarcadores) {
            String mDrawableName = tipo.toLowerCase() + m.getNivel();
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(m.getLatitud(), m.getLongitud()))
                    .title(m.getTitulo())
                    .icon(BitmapDescriptorFactory
                            .fromResource(resID)));
        }
        googleMap.setOnMarkerClickListener(this);
        checkGeoPermission();

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String alerta = "";
        String mensaje = "";
        LatLng latLng = marker.getPosition();
        for (Marcador m: listaMarcadores) {
            if(m.getLatitud()==latLng.latitude) alerta = m.getNivel();
        }
        switch (alerta){
            case "yellow":
                mensaje = "AMARILLA";
                break;
            case "green":
                mensaje = "VERDE";
                break;
            case "black":
                mensaje = "NEGRA";
                break;
            case "orange":
                mensaje = "NARANJA";
                break;
            case "red":
                mensaje = "ROJA";
                break;
        }
        Toast.makeText(MapsActivity.this, "ALERTA COLOR: "+ mensaje, Toast.LENGTH_SHORT).show();
        return false;
    }

    private void checkGeoPermission() {
        // Checks if the geolocation permission has been granted by the user.
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            enableUserLocation();
            zoomToUserLocation();
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                //It shows the user a dialog why this permission is neccessary
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void enableUserLocation(){
        mMap.setMyLocationEnabled(true);
    }

    @SuppressLint("MissingPermission")
    private void zoomToUserLocation(){
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(location -> {
            LatLng lastLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 14f));
        }); // locationTask listener
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_LOCATION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                enableUserLocation();
                zoomToUserLocation();
            } else {
                // It shows the user a dialog that the geolocation permission hasn't been granted
                Toast.makeText(this, "Información de ubicación denegada.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
