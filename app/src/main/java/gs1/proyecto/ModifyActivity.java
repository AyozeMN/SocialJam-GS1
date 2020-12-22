package gs1.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    private TextView tv_tittle;
    private ListView lv_todo;
    private RadioGroup rg_niveles;
    private RadioButton rb_green, rb_yellow, rb_orange, rb_red, rb_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify2);
        initializeViewComponents();
    }

    private void initializeViewComponents() {
        tv_tittle = findViewById(R.id.tv_tittle);
        lv_todo = findViewById(R.id.lv_todo);
        rg_niveles = findViewById(R.id.rg_niveles);
        rb_green = findViewById(R.id.rb_green);
        rb_yellow = findViewById(R.id.rb_yellow);
        rb_orange = findViewById(R.id.rb_orange);
        rb_red = findViewById(R.id.rb_red);
        rb_black = findViewById(R.id.rb_black);
    }
}