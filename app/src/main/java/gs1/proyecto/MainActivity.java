package gs1.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btnBibliotecas, btnCines, btnComidaRapida, btnCorreos, btnGasolineras, btnGimnasios, btnMuseos, btnPlayas, btnTiendasRopa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initializeViewComponents();

        btnBibliotecas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivityBibliotecas.class);
                startActivity(intent);
            }
        });

        btnPlayas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivityPlayas.class);
                startActivity(intent);
            }
        });
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