package gs1.proyecto;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityPlayas extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    //Mostrar info implementamos GoogleMap.OnMarkerClickListener
    private Marker mpAlcaravaneras, mpConfital, mpCicer, mpPenavieja, mpChica, mpGrande, mpPuntilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_playas);
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
        mMap.addMarker(new MarkerOptions().position(lp).title("Marker in Las Palmas").snippet("Ciudad de Las Palmas de Gran Canaria"));

        //Permitir zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //My location
        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        //mMap.setMyLocationEnabled(true);
        //mMap.getUiSettings().setMyLocationButtonEnabled(true);

        //Se puede añadir el zoom directamente aquí: mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lp, 10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lp));
        //Valor mínimo del zoom es 2.0 y máximo 21.0
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        LatLng pAlcaravaneras = new LatLng(28.130040218622792, -15.429274438906358);
        mpAlcaravaneras = googleMap.addMarker(new MarkerOptions().position(pAlcaravaneras).title("Playa de las Alcaravaneras").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //Para cambiar color de icono usamos .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        //.addMarker(new MarkerOptions().position(pAlcaravaneras).title("Playa de las Alcaravaneras").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        LatLng pConfital = new LatLng(28.159831539195743, -15.43552598234442);
        mpConfital = googleMap.addMarker(new MarkerOptions().position(pConfital).title("Playa del Confital").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //mMap.addMarker(new MarkerOptions().position(pConfital).title("Playa del Confital").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        LatLng pCicer = new LatLng(28.131981065294294, -15.444440917128023);
        mpCicer = googleMap.addMarker(new MarkerOptions().position(pCicer).title("Playa la Cícer").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //mMap.addMarker(new MarkerOptions().position(pCicer).title("Playa la Cícer").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        LatLng pPenavieja = new LatLng(28.137014397171363, -15.438368395951127);
        mpPenavieja = googleMap.addMarker(new MarkerOptions().position(pPenavieja).title("Playa Peña de la Vieja").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //mMap.addMarker(new MarkerOptions().position(pPenavieja).title("Playa Peña de la Vieja").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        LatLng pChica = new LatLng(28.14059056826637, -15.436158255634496);
        mpChica = googleMap.addMarker(new MarkerOptions().position(pChica).title("Playa Chica").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //mMap.addMarker(new MarkerOptions().position(pChica).title("Playa Chica").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        LatLng pGrande = new LatLng(28.144015255041584, -15.432746485901356);
        mpGrande = googleMap.addMarker(new MarkerOptions().position(pGrande).title("Playa Grande").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //mMap.addMarker(new MarkerOptions().position(pGrande).title("Playa Grande").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        LatLng pPuntilla = new LatLng(28.14831014942326, -15.431394652610866);
        mpPuntilla = googleMap.addMarker(new MarkerOptions().position(pPuntilla).title("Playa la Puntilla").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));
        //mMap.addMarker(new MarkerOptions().position(pPuntilla).title("Playa la Puntilla").icon(BitmapDescriptorFactory.fromResource(R.drawable.beach)));

        //Para que escuche los clicks
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //Vamos a mostrar alerta diciendo que el nivel de peligro las alcaravaneras es negro
        if(marker.equals(mpAlcaravaneras)) {
            Toast.makeText(this, "NIVEL amarillo, PELIGRO", Toast.LENGTH_SHORT).show();
        }
        if (marker.equals(mpConfital)) {
            Toast.makeText(this, "verde jjj", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}