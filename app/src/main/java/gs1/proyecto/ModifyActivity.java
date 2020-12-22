package gs1.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.net.SocketOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModifyActivity extends AppCompatActivity {

    private ListView lv_todo;
    private RadioGroup radioGroup;
    private RadioButton rb_G, rb_Y, rb_O, rb_R, rb_B;
    private Button bt_actualizar;
    private String level;
    private BaseDeDatos baseDeDatos;
    private List<Marcador> listaMarcadores;
    private ArrayAdapter adapterTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify2);
        Objects.requireNonNull(getSupportActionBar()).hide();

        baseDeDatos = new BaseDeDatos(ModifyActivity.this);

        radioGroup = findViewById(R.id.rg_niveles);
        lv_todo = findViewById(R.id.lv_todo);
        bt_actualizar = findViewById(R.id.btn_actualizar);

        lv_todo.setOnItemClickListener((parent, view, position, id) -> {
            lv_todo.setSelection(position);
            getAlertLevel(position);
        });
        bt_actualizar.setOnClickListener(v -> changeLevel());

        showEverything(baseDeDatos);
    }

    private void getAlertLevel(int position) {
        Map<String, Integer> levels = new HashMap<String, Integer>();
        levels.put("green", 0);
        levels.put("yellow", 1);
        levels.put("orange", 2);
        levels.put("red", 3);
        levels.put("black", 4);
        String level = listaMarcadores.get(position).getNivel();
        radioGroup.check(radioGroup.getChildAt(levels.get(level)).getId());
    }

    private void changeLevel() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String nivelAlerta = radioButton.getText().toString().toLowerCase();
        //TODO: CAMBIAR EN LA BASE DE DATOS EL NIVEL DE ALERTA
    }

    private void showEverything(BaseDeDatos baseDeDatos) {
        listaMarcadores = baseDeDatos.getEverything();
        List<String> nameList = new ArrayList();
        for (Marcador m : listaMarcadores) {
            nameList.add(m.getTitulo());
        }
        adapterTodo = new ArrayAdapter<>(ModifyActivity.this, android.R.layout.simple_list_item_1, nameList);
        lv_todo.setAdapter(adapterTodo);
    }

    private void callMapActivity(String nivel) {
        Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
        intent.putExtra("type", nivel);
        startActivity(intent);
    }

}