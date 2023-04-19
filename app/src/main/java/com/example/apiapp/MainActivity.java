package com.example.apiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String key = "yNyg0AMeLuPdBjE7Q84wVg==0wTb8nJHdRkOgD2p";
    String linkURL = "https://dog.ceo/api/breeds/image/random";
    TextView textView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // help from https://www.tutorialspoint.com/how-to-use-volley-string-request-in-android
        textView = findViewById(R.id.textBox);
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, linkURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response.toString());
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
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