package gs1.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btnBibliotecas,btnCines,btnComidaRapida,btnCorreos,btnGasolineras,btnGimnasios,btnMuseos,btnPlayas,btnTiendasRopa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btnBibliotecas = findViewById(R.id.btn_Bibliotecas);
        btnCines = findViewById(R.id.btn_Cines);
        btnComidaRapida = findViewById(R.id.btn_ComidaRapida);
        btnCorreos = findViewById(R.id.btn_Correos);
        btnGasolineras = findViewById(R.id.btn_Gasolineras);
        btnGimnasios = findViewById(R.id.btn_Gimnasios);
        btnMuseos = findViewById(R.id.btn_Museos);
        btnPlayas = findViewById(R.id.btn_Playas);
        btnTiendasRopa = findViewById(R.id.btn_TiendasRopa);

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
}