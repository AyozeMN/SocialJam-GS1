package gs1.proyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private EditText tv_name;
    private EditText tv_surname;
    private EditText tv_password;
    private EditText tv_password2;
    private EditText tv_email;
    private Button bt_next;
    private Button bt_back;
    private ArrayList <EditText> fields = new ArrayList<>();
    private TextView tv_error;
    private TextView tv_title;
    private int screen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        tv_name = findViewById(R.id.editTextTextPersonName);
        tv_surname = findViewById(R.id.editTextTextPersonSurname);
        tv_email = findViewById(R.id.editTextTextEmailAddress);
        tv_password = findViewById(R.id.editTextTextPassword);
        tv_password2 = findViewById(R.id.editTextTextPassword2);
        tv_title = findViewById(R.id.tv_title);
        tv_error = findViewById(R.id.tv_error);
        fields.add(tv_name);
        fields.add(tv_surname);
        fields.add(tv_email);
        fields.add(tv_password);
        fields.add(tv_password2);
        bt_back = findViewById(R.id.buttonBack);
        bt_next = findViewById(R.id.buttonNext);
        tv_error.setText("");

        //Volver a la pantalla anterior
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(screen==0)
                    finish();
                else{
                    screen--;
                    setFormVisibility(View.VISIBLE);
                }
            }
        });

        //avanzar de pantalla
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataValid() && screen==0) {
                    screen++;
                    setFormVisibility(View.GONE);
                    askLocationServicesPermission();
                } if (screen==1) {
                    screen++;
                    // Que mostrar en la pantalla numero 2
                }
            }
        });
    }

    private void askLocationServicesPermission() {

    }

    private boolean isDataValid() {
        for (EditText field : fields) {
            if(field.getText().toString().length() < 3){
                tv_error.setText(String.format("El campo %s debe tener más de 2 carácteres", field.getHint().toString()));
                return false;
            } if(!tv_email.getText().toString().contains(".")){ //cambiar por arroba cuando termine de implementarse
                tv_error.setText("El campo Email no es válido");
                return false;
            } if (!tv_password.getText().toString().equals(tv_password2.getText().toString())){
                tv_error.setText("Las contraseñas no coinciden");
                return false;
            }
        }
        tv_error.setText("");
        return true;
    }

    private void setFormVisibility(int visibility) {
        tv_title.setVisibility(visibility);
        tv_error.setVisibility(visibility);
        for (EditText field : fields) {
            field.setVisibility(visibility);
        }
    }
}