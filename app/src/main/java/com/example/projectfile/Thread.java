package com.example.projectfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Thread extends AppCompatActivity {

    //서버로 요청하는 객체
    private RequestQueue queue;
    //서버로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //클라이언트를 판별하는 값
    private String TAG = "main";

    boolean popupCheck = true;
    int tilt_check=0;
    int motion_check=-1;
    int impact_check=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);

        java.lang.Thread thread = new java.lang.Thread(new ChatThread(this));
        thread.start();
    }

    public class ChatThread implements Runnable{

        public ChatThread(Thread mainActivity) {
        }

        @Override
        public void run() {
            while(true){
                getsensor();
                try {
                    java.lang.Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getsensor() {
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
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(tilt_check==0){
                        tilt_check = jsonArray.getJSONObject(0).getInt("sensora");
                        motion_check = jsonArray.getJSONObject(0).getInt("sensorb");
                        impact_check = jsonArray.getJSONObject(0).getInt("sensorc");
                    }
                    if(jsonArray.getJSONObject(0).getInt("sensora")!=tilt_check){
                        tilt_check = jsonArray.getJSONObject(0).getInt("sensora");
                        Intent intent = new Intent(Thread.this, Popup.class);
                        intent.putExtra("text","1");
                        startActivity(intent);
                        finish();
                    }
                    if(jsonArray.getJSONObject(0).getInt("sensorb")!=motion_check){
                        motion_check = jsonArray.getJSONObject(0).getInt("sensorb");
                        Intent intent = new Intent(Thread.this, Popup.class);
                        intent.putExtra("text","2");
                        startActivity(intent);
                        finish();
                    }
                    if(jsonArray.getJSONObject(0).getInt("sensorc")!=impact_check){
                        impact_check = jsonArray.getJSONObject(0).getInt("sensorc");
                        Intent intent = new Intent(Thread.this, Popup.class);
                        intent.putExtra("text","3");
                        intent.putExtra("bgColor", "red");
                        startActivity(intent);
                        finish();
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