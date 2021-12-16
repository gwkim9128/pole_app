package com.example.projectfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    Button showDialog;
    ArrayList<ListVO> items = new ArrayList<ListVO>();
    TextView tv_list;
    ListView listView;
    InputMethodManager imm;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Adapter adapter;

        adapter = new Adapter();

        searchView = findViewById(R.id.searchView);
        tv_list = findViewById(R.id.tv_list);
        showDialog = findViewById(R.id.showDialog);
        listView = (ListView) findViewById(R.id.listView);
        // 키보드 제어 (키보드 보이기)
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE); // 입력 방법을 관리
        listView.setAdapter(adapter);

        adapter.addItem("전주 번호", "담당 사업소", "위치");
        adapter.addItem("1001", "동구 지사", "풍암동 22번지");
        adapter.addItem("1002", "서구 지사", "금호동 11번지");
        adapter.addItem("1003", "남구 지사", "봉선동 32번지");
        adapter.addItem("1004", "북구 지사", "지산동 66번지");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");
        adapter.addItem("", "", "");

        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this, Thread.class);
                startActivity(intent);
                finish();
            }
        });
    }
}



