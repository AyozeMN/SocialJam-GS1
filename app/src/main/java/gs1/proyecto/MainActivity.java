package gs1.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnBibliotecas, btnCines, btnComidaRapida, btnCorreos, btnGasolineras, btnGimnasios, btnMuseos, btnPlayas, btnTiendasRopa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initializeViewComponents();
        initializeButtons();
    }

    private void initializeButtons() {
        btnBibliotecas.setOnClickListener(v -> callMapActivity("BIBLIOTECAS"));
        btnCines.setOnClickListener(v -> callMapActivity("CINES"));
        btnComidaRapida.setOnClickListener(v -> callMapActivity("COMIDARAPIDA"));
        btnCorreos.setOnClickListener(v -> callMapActivity("CORREOS"));
        btnGasolineras.setOnClickListener(v -> callMapActivity("GASOLINERAS"));
        btnGimnasios.setOnClickListener(v -> callMapActivity("GIMNASIOS"));
        btnMuseos.setOnClickListener(v -> callMapActivity("MUSEOS"));
        btnPlayas.setOnClickListener(v -> callMapActivity("PLAYAS"));
        btnTiendasRopa.setOnClickListener(v -> callMapActivity("TIENDAS"));
    }

    private void callMapActivity(String filter) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("type", filter);
        startActivity(intent);
    }

    private void initializeViewComponents() {
        btnComidaRapida = findViewById(R.id.btn_ComidaRapida);
        btnBibliotecas = findViewById(R.id.btn_Bibliotecas);
        btnGasolineras = findViewById(R.id.btn_Gasolineras);
        btnTiendasRopa = findViewById(R.id.btn_TiendasRopa);
        btnGimnasios = findViewById(R.id.btn_Gimnasios);
        btnCorreos = findViewById(R.id.btn_Correos);
        btnMuseos = findViewById(R.id.btn_Museos);
        btnPlayas = findViewById(R.id.btn_Playas);
        btnCines = findViewById(R.id.btn_Cines);
    }
}