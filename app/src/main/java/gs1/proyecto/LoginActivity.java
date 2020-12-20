package gs1.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText email_tv, password_tv;
    private Button login_button, register_button;
    private TextView failedLogin_tv, ornament;
    private ImageView mainLogo, logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initializeViewComponents();
        showLogo();

        // Valida los datos al presionar el boton de iniciar sesion
        login_button.setOnClickListener(v -> validate(email_tv.getText().toString(), password_tv.getText().toString()));

        //LLama a la actividad para crear usuario
        register_button.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
            startActivity(intent);
        });

        //Borra el mensaje de contraseña erronea
        password_tv.setOnClickListener(v -> failedLogin_tv.setVisibility(View.GONE));
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

    private void initializeViewComponents() {
        register_button = findViewById(R.id.registerButton);
        failedLogin_tv = findViewById(R.id.tv_loginfailed);
        password_tv = findViewById(R.id.editTextPassword);
        login_button = findViewById(R.id.loginButton);
        email_tv = findViewById(R.id.editTextEmail);
        mainLogo = findViewById(R.id.iv_mainlogo);
        ornament = findViewById(R.id.textView);
        logo = findViewById(R.id.imageView);
    }

    private void showLogo() {
        new Handler().postDelayed(() -> {
            mainLogo.setVisibility(View.GONE);
            email_tv.setVisibility(View.VISIBLE);
            password_tv.setVisibility(View.VISIBLE);
            login_button.setVisibility(View.VISIBLE);
            register_button.setVisibility(View.VISIBLE);
            ornament.setVisibility(View.VISIBLE);
            logo.setVisibility(View.VISIBLE);
        }, 3000);
    }
}