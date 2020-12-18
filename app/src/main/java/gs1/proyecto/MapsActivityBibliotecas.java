package gs1.proyecto;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityBibliotecas extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker mbMunicipalJosefina,mbMunicipalIsabel,mbPublicaEstado,mbEspacioJoven,mbMunicipalRehoyas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_bibliotecas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        LatLng lp = new LatLng(28.134457, -15.435111);
        mMap.addMarker(new MarkerOptions().position(lp).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lp));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

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
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}