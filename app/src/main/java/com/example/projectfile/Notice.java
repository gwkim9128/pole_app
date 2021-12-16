package com.example.projectfile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Notice extends AppCompatActivity {

    Button btn_tilt,btn_motion,btn_impact;
    ListView lv_tilt,lv_motion,lv_impact;
    InputMethodManager imm;
    Adapter_Tilt adapter_tilt;
    Adapter_Motion adapter_motion;
    Adapter_Impact adapter_impact;

    //서버로 요청하는 객체
    private RequestQueue queue;
    //서버로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //클라이언트를 판별하는 값
    private String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);

        btn_tilt = findViewById(R.id.btn_tilt);
        btn_motion = findViewById(R.id.btn_motion);
        btn_impact = findViewById(R.id.btn_impact);
        lv_tilt = findViewById(R.id.lv_tilt);
        lv_motion = findViewById(R.id.lv_motion);
        lv_impact = findViewById(R.id.lv_impact);

        Intent intent = getIntent();
        String a = intent.getStringExtra("num");
        String color = intent.getStringExtra("bgColor");
//        String a = "2";
        Log.d("테스트2", a);

        if(a.equals("1")){
            btn_tilt.setBackgroundColor(Color.parseColor(color));
        } else if(a.equals("2")){
            btn_motion.setBackgroundColor(Color.parseColor(color));
        } else if(a.equals("3")){
            btn_impact.setBackgroundColor(Color.parseColor(color));
        }

        sendRequest1();
        sendRequest2();
        sendRequest3();

        btn_tilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_tilt.setVisibility(View.VISIBLE);
                lv_motion.setVisibility(View.INVISIBLE);
                lv_impact.setVisibility(View.INVISIBLE);

                if(a.equals("1") && color.equals("red")){
                    btn_tilt.setBackgroundColor(Color.parseColor("#C19C3A"));
                }

//                btn1.setBackgroundColor(Color.parseColor("#FFCB64"));
//                btn2.setBackgroundColor(Color.parseColor("#C19C3A"));
//                btn3.setBackgroundColor(Color.parseColor("#C19C3A"));
                sendRequest1();
            }
        });
        btn_motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_tilt.setVisibility(View.INVISIBLE);
                lv_motion.setVisibility(View.VISIBLE);
                lv_impact.setVisibility(View.INVISIBLE);

                if(a.equals("2") && color.equals("red")){
                    btn_motion.setBackgroundColor(Color.parseColor("#C19C3A"));
                }
//                btn1.setBackgroundColor(Color.parseColor("#C19C3A"));
//                btn2.setBackgroundColor(Color.parseColor("#FFCB64"));
//                btn3.setBackgroundColor(Color.parseColor("#C19C3A"));
                sendRequest2();
            }
        });
        btn_impact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_tilt.setVisibility(View.INVISIBLE);
                lv_motion.setVisibility(View.INVISIBLE);
                lv_impact.setVisibility(View.VISIBLE);

                if(a.equals("3") && color.equals("red")){
                    btn_impact.setBackgroundColor(Color.parseColor("#C19C3A"));
                }

//                btn1.setBackgroundColor(Color.parseColor("#C19C3A"));
//                btn2.setBackgroundColor(Color.parseColor("#C19C3A"));
//                btn3.setBackgroundColor(Color.parseColor("#FFCB64"));
                sendRequest3();
            }
        });
    }

    public void sendRequest1() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://121.147.0.157:8087/AndroidServer/kgwList";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                adapter_tilt = new Adapter_Tilt();
                lv_tilt = findViewById(R.id.lv_tilt);
                lv_tilt.setAdapter(adapter_tilt);
                imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        adapter_tilt.addItem(jsonObject.getInt("sensora"),"기울기 변화가 감지되었습니다.",
                                jsonObject.getString("day"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

    public void sendRequest2() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://121.147.0.157:8087/AndroidServer/kgwList";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                adapter_motion = new Adapter_Motion();
                lv_motion = findViewById(R.id.lv_motion);
                lv_motion.setAdapter(adapter_motion);
                imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        adapter_motion.addItem(jsonObject.getInt("sensorb"),"조류, 둥지 등이 감지 되었습니다.",
                                jsonObject.getString("day"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

    public void sendRequest3() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://121.147.0.157:8087/AndroidServer/kgwList";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                adapter_impact = new Adapter_Impact();
                lv_impact = findViewById(R.id.lv_impact);
                lv_impact.setAdapter(adapter_impact);
                imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        adapter_impact.addItem(jsonObject.getInt("sensorc"),"충격이 감지되었습니다.",
                                jsonObject.getString("day"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

}