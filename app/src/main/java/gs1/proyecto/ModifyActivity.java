package gs1.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModifyActivity extends AppCompatActivity {

    private ListView lv_todo;
    private Button bt_actualizar;
    private String level;
    private BaseDeDatos baseDeDatos;
    private ArrayAdapter adapterTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify2);
        Objects.requireNonNull(getSupportActionBar()).hide();

        baseDeDatos = new BaseDeDatos(ModifyActivity.this);

        showEverything(baseDeDatos);

        bt_actualizar = findViewById(R.id.btn_actualizar);
        bt_actualizar.setOnClickListener(v -> callMapActivity(changeLevel()));
    }

    private String changeLevel() {
        switch (R.id.rg_niveles) {
            case R.id.rb_green:
                level = "green";
                break;
            case R.id.rb_yellow:
                level = "yellow";
                break;
            case R.id.rb_orange:
                level = "orange";
                break;
            case R.id.rb_red:
                level = "red";
                break;
            case R.id.rb_black:
                level = "black";
                break;
        }

        return level;
    }

    private void showEverything(BaseDeDatos baseDeDatos1) {
        adapterTodo = new ArrayAdapter<Marcador>(ModifyActivity.this, android.R.layout.simple_list_item_1, baseDeDatos1.getEverything());
        lv_todo.setAdapter(adapterTodo);
    }

    private void callMapActivity(String nivel) {
        Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
        intent.putExtra("type", nivel);
        startActivity(intent);
    }

}