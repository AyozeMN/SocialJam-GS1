package gs1.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText email_tv;
    private EditText password_tv;
    private Button login_button;
    private Button register_button;
    private TextView failedLogin_tv;
    private ImageView mainLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();

        email_tv = findViewById(R.id.editTextEmail);
        password_tv = findViewById(R.id.editTextPassword);
        login_button = findViewById(R.id.loginButton);
        register_button = findViewById(R.id.registerButton);
        failedLogin_tv = findViewById(R.id.tv_loginfailed);
        mainLogo = findViewById(R.id.iv_mainlogo);

        // Muestra el logo al iniciar la aplicacion
//        int TIME_IN_MILLIS_LOGO_IS_SHOWN = 3000;
//        mainLogo.setVisibility(View.VISIBLE);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                mainLogo.setVisibility(View.GONE);
//            }
//        }, TIME_IN_MILLIS_LOGO_IS_SHOWN);

        // Valida los datos al presionar el boton de iniciar sesion
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email_tv.getText().toString(), password_tv.getText().toString());
            }
        });

        //LLama a la actividad para crear usuario
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        //Borra el mensaje de contraseña erronea
        password_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                failedLogin_tv.setVisibility(View.GONE);
            }
        });
    }

    // llama a la actividad correspondiente o
    // muestra mensaje de contraseña erronea
    private void validate(String email, String password){
        if(email.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, ModifyActivity.class);
            startActivity(intent);
        }
        else if (email.equals("user") && password.equals("user")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            password_tv.setText("");
            failedLogin_tv.setVisibility(View.VISIBLE);
        }
    }
}