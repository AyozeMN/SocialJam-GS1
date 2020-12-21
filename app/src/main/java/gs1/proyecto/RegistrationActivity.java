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
import java.util.List;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private ArrayList <EditText> fields = new ArrayList<>();
    private TextView tv_error;
    private TextView tv_title;
    private int screen = 0;
    EditText et_usuario, et_nombre, et_email, et_pass, et_pass2;
    ListView lv_userList;
    Button bt_back, bt_next, bt_viewUsers;
    Switch sw_admin;
    ArrayAdapter userArrayAdapter;
    BaseDeDatos baseDeDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bt_back = findViewById(R.id.btn_back);
        bt_next = findViewById(R.id.btn_next);
        bt_viewUsers = findViewById(R.id.btn_viewUsers);

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

        baseDeDatos = new BaseDeDatos(RegistrationActivity.this);

        showUsersOnListView(baseDeDatos);

        //Volver a la pantalla anterior
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(screen==0)
                    finish();
                else{
                    screen--;
                    setFormVisibility(View.VISIBLE);
                }*/
            }
        });

        //avanzar de pantalla
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(isDataValid() && screen==0) {
                    screen++;
                    setFormVisibility(View.GONE);
                    askLocationServicesPermission();
                } if (screen==1) {
                    screen++;
                    // Que mostrar en la pantalla numero 2
                }*/

                Users user;

                if(isDataValid()) {
                    user = new Users(-1, et_usuario.getText().toString(), et_nombre.getText().toString(), et_email.getText().toString(), et_pass.getText().toString(), sw_admin.isChecked());
                    Toast.makeText(RegistrationActivity.this,user.toString(),Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    user = new Users(-1, "error", "error", "error", "error", false);
                }

                BaseDeDatos baseDeDatos = new BaseDeDatos(RegistrationActivity.this);

                boolean success = baseDeDatos.addOne(user);

                //Toast.makeText(RegistrationActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                showUsersOnListView(baseDeDatos);

            }
        });

        bt_viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos baseDeDatos = new BaseDeDatos(RegistrationActivity.this);

                //Metemos todos en la lista
                showUsersOnListView(baseDeDatos);
            }
        });

        lv_userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users clickedUsuarios = (Users) parent.getItemAtPosition(position);
                baseDeDatos.deleteOne(clickedUsuarios);
                showUsersOnListView(baseDeDatos);
                Toast.makeText(RegistrationActivity.this, "Deleted " + clickedUsuarios.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUsersOnListView(BaseDeDatos baseDeDatos2) {
        userArrayAdapter = new ArrayAdapter<Users>(RegistrationActivity.this, android.R.layout.simple_list_item_1, baseDeDatos2.getEveryone());
        lv_userList.setAdapter(userArrayAdapter);
    }

    private void askLocationServicesPermission() {

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

    /*private void setFormVisibility(int visibility) {
        tv_title.setVisibility(visibility);
        tv_error.setVisibility(visibility);
        for (EditText field : fields) {
            field.setVisibility(visibility);
        }
    }*/
}