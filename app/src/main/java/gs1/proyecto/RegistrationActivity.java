package gs1.proyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    private ArrayList <EditText> fields = new ArrayList<>();
    private TextView tv_error;
    EditText et_usuario, et_nombre, et_email, et_pass, et_pass2;
    ListView lv_userList;
    Button bt_back, bt_next, bt_viewUsers;
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

        tv_error = findViewById(R.id.tv_error);

        tv_error.setText("");

        baseDeDatos = new BaseDeDatos(RegistrationActivity.this);

        showUsersOnListView(baseDeDatos);

        //Volver a la pantalla anterior
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //avanzar de pantalla
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;

                if(isDataValid()) {
                    user = new User(-1, et_usuario.getText().toString(), et_nombre.getText().toString(), et_email.getText().toString(), et_pass.getText().toString());
                    Toast.makeText(RegistrationActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                    if(addUserToDB(user))
                        showUsersOnListView(baseDeDatos);
                }
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
                User clickedUsuarios = (User) parent.getItemAtPosition(position);
                baseDeDatos.deleteOne(clickedUsuarios);
                showUsersOnListView(baseDeDatos);
                Toast.makeText(RegistrationActivity.this, "Deleted " + clickedUsuarios.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUsersOnListView(BaseDeDatos baseDeDatos2) {
        userArrayAdapter = new ArrayAdapter<User>(RegistrationActivity.this, android.R.layout.simple_list_item_1, baseDeDatos2.getEveryone());
        lv_userList.setAdapter(userArrayAdapter);
    }

    private boolean addUserToDB(User user){
        BaseDeDatos baseDeDatos = new BaseDeDatos(RegistrationActivity.this);
        return baseDeDatos.addOne(user);
    }

    private boolean isDataValid() {
        for (EditText field : fields) {
            if(field.getText().toString().length() < 3){
                tv_error.setText(String.format("El campo %s debe tener más de 2 carácteres", field.getHint().toString()));
                return false;
            } if(!et_email.getText().toString().contains(".")){ //TODO: cambiar por arroba cuando termine de implementarse
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