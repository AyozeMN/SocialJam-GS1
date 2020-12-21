package gs1.proyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private ArrayList <EditText> fields = new ArrayList<>();
    private TextView tv_error;
    private TextView tv_title;
    private int screen = 0;
    private EditText et_usuario, et_nombre, et_email, et_pass, et_pass2;
    private ListView lv_userList;
    private Button bt_back, bt_next;
    private Switch sw_admin;
    private BaseDeDatos baseDeDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initializeViewComponents();

        baseDeDatos = new BaseDeDatos(RegistrationActivity.this);

        //Volver a la pantalla anterior
        bt_back.setOnClickListener(v -> finish());

        //avanzar de pantalla
        bt_next.setOnClickListener(v -> {
            if(isDataValid()) {
                User user = new User(-1, et_usuario.getText().toString(), et_nombre.getText().toString(), et_email.getText().toString(), et_pass.getText().toString(), sw_admin.isChecked());
                BaseDeDatos baseDeDatos = new BaseDeDatos(RegistrationActivity.this);
                boolean success = baseDeDatos.addOne(user);
            } else {
                tv_error.setText("La información no es válida");
            }
            finish();
        });

        lv_userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User clickedUsuarios = (User) parent.getItemAtPosition(position);
                baseDeDatos.deleteOne(clickedUsuarios);
                Toast.makeText(RegistrationActivity.this, "Deleted " + clickedUsuarios.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeViewComponents() {
        bt_back = findViewById(R.id.btn_back);
        bt_next = findViewById(R.id.btn_next);
        sw_admin = findViewById(R.id.sw_admin);
        lv_userList = findViewById(R.id.lv_userList);
        et_usuario = findViewById(R.id.et_usuario);
        et_nombre = findViewById(R.id.et_nombre);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_pass2 = findViewById(R.id.et_pass2);
        fields.add(et_usuario);
        fields.add(et_nombre);
        fields.add(et_email);
        fields.add(et_pass);
        fields.add(et_pass2);
        tv_title = findViewById(R.id.tv_title);
        tv_error = findViewById(R.id.tv_error);
        tv_error.setText("");
    }

    private boolean isDataValid() {
        for (EditText field : fields) {
            if(field.getText().toString().length() < 3){
                tv_error.setText(String.format("El campo %s debe tener más de 2 carácteres", field.getHint().toString()));
                return false;
            } if(!et_email.getText().toString().contains(".")){ //cambiar por arroba cuando termine de implementarse
                tv_error.setText("El campo Email no es válido");
                return false;
            } if (!et_pass.getText().toString().equals(et_pass2.getText().toString())){
                tv_error.setText("Las contraseñas no coinciden");
                return false;
            }
        }
        tv_error.setText("");
        return true;
    }

}