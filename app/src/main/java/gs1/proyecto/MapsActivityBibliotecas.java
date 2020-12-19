package gs1.proyecto;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

public class MapsActivityBibliotecas extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker mbMunicipalJosefina,mbMunicipalIsabel,mbPublicaEstado,mbEspacioJoven,mbMunicipalRehoyas;
    // Unique code to check if the location has been requested.
    private int ACCESS_LOCATION_REQUEST_CODE = 10001;
    public FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_bibliotecas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // gets current user location fix on the device
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
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

        //LatLng lp = new LatLng(28.134457, -15.435111);
        //mMap.addMarker(new MarkerOptions().position(lp).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(lp));
       // mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        zoomToUserLocation();

        LatLng bMunicipalJosefina = new LatLng(28.134398455809993, -15.440590627979137);
        mbMunicipalJosefina = googleMap.addMarker(new MarkerOptions().position(bMunicipalJosefina).title("Biblioteca Municipal Josefina De La Torre").icon(BitmapDescriptorFactory.fromResource(R.drawable.library)));
        LatLng bMunicipalIsabel = new LatLng(28.11933934804811, -15.436335433789726);
        mbMunicipalIsabel = googleMap.addMarker(new MarkerOptions().position(bMunicipalIsabel).title("Biblioteca Municipal Isabel la Católica").icon(BitmapDescriptorFactory.fromResource(R.drawable.library)));
        LatLng bPublicaEstado = new LatLng(28.110385067953494, -15.416181363900156);
        mbPublicaEstado = googleMap.addMarker(new MarkerOptions().position(bPublicaEstado).title("Biblioteca Pública del Estado").icon(BitmapDescriptorFactory.fromResource(R.drawable.library)));
        LatLng bEspacioJoven = new LatLng(28.111764509885216, -15.423878626939796);
        mbEspacioJoven = googleMap.addMarker(new MarkerOptions().position(bEspacioJoven).title("Biblioteca Juvenil Espacio Joven").icon(BitmapDescriptorFactory.fromResource(R.drawable.library)));
        LatLng bRehoyas = new LatLng(28.108392244556118, -15.431879202015086);
        mbMunicipalRehoyas = googleMap.addMarker(new MarkerOptions().position(bRehoyas).title("Biblioteca Municipal Las Rehoyas").icon(BitmapDescriptorFactory.fromResource(R.drawable.library)));

        googleMap.setOnMarkerClickListener(this);

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

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
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
            } else {
                // It shows the user a dialog that the geolocation permission hasn't been granted
                Toast.makeText(this, "Información de ubicación denegada.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}