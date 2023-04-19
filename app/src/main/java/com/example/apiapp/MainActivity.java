package com.example.apiapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    String linkURL = "https://dog.ceo/api/breeds/image/random";
    TextView textView;
    ImageView imageView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button to call request method
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> imgRequest());

        // help from https://www.tutorialspoint.com/how-to-use-volley-string-request-in-android
        textView = findViewById(R.id.textBox);
        imageView = findViewById(R.id.imageView);
        queue = Volley.newRequestQueue(this);
        imgRequest();
    }

    // Method for requesting and setting dog images.
    public void imgRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, linkURL, response -> {
            String url = response.substring(response.indexOf("\"h")+1, response.lastIndexOf("\","));
            String breed = response.substring(response.indexOf("s\\/")+3, response.lastIndexOf("\\/"));
            Picasso.get().load(url).into(imageView);
            textView.setText(breed);
        }, error -> Log.d("error",error.toString()));
        queue.add(request);
    }
}