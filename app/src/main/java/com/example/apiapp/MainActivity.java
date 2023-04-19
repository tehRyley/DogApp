package com.example.apiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    String key = "yNyg0AMeLuPdBjE7Q84wVg==0wTb8nJHdRkOgD2p";
    String linkURL = "https://dog.ceo/api/breeds/image/random";
    TextView textView;
    ImageView imageView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // help from https://www.tutorialspoint.com/how-to-use-volley-string-request-in-android
        textView = findViewById(R.id.textBox);
        imageView = findViewById(R.id.imageView);
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, linkURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String trimRes = response.toString().substring(response.indexOf("\"h")+1, response.lastIndexOf("\","));
                Picasso.get().load(trimRes).into(imageView);
                textView.setText(response.toString());
                //Toast.makeText(MainActivity.this,trimRes,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);
    }
}