package com.example.projectfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Popup extends Activity {

    TextView tv;
    Button btn_close, btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        tv = findViewById(R.id.tv);
        btn_close = findViewById(R.id.btn_close);
        btn_confirm = findViewById(R.id.btn_confirm);

        //코드추가
        Intent intent = getIntent();
        String a = intent.getStringExtra("text");
        if(a.equals("1")){
            tv.setText("00번 전신주에 기울기 변화가 감지되었습니다.");
        } else if (a.equals("2")){
            tv.setText("00번 전신주에 센서값 변화가 감지되었습니다.");
        } else if(a.equals("3")){
            tv.setText("00번 전신주에 충격이 감지되었습니다.");
        }

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Popup.this, Notice.class);
                intent.putExtra("num",a);
                intent.putExtra("bgColor","red");
                Log.d("테스트1", a);
                startActivity(intent);
                finish();
            }
        });
    }
}