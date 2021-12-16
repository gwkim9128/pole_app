package com.example.projectfile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    ImageView imageView;
    TextView textView, textView2;
    EditText edt_id, edt_pw;
    Button btn_login;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        checkBox = findViewById(R.id.checkBox);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText().toString();
                String pw = edt_pw.getText().toString();
                if(id.equals("smart")&&pw.equals("1234")){
                    Intent intent = new Intent(Login.this, List.class);
                    intent.putExtra("msg", id);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences prefer = getSharedPreferences("temp", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefer.edit();

                if(isChecked){
                    editor.putString("id", edt_id.getText().toString());
                    editor.putBoolean("cb", checkBox.isChecked());
                }else{
                    editor.clear();
                }
                editor.commit();
                Log.d("LoginActivity", "체크상태: "+(isChecked?"체크됨":"체크풀림")+"");
            }
        });

        SharedPreferences sp = getSharedPreferences("temp", MODE_PRIVATE);

        String s = sp.getString("id", "");
        Boolean b = sp.getBoolean("cb", false);

        edt_id.setText(s);
        checkBox.setChecked(b);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
