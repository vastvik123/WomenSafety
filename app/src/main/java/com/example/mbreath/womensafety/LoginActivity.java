package com.example.mbreath.womensafety;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText emailEditText;
    EditText passwordEditText;

    Button loginButton;

    Button signupButton;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.loginemailedittextid);
        passwordEditText = findViewById(R.id.loginpasswordedittextid);

        loginButton = findViewById(R.id.loginbuttonid);

        signupButton = findViewById(R.id.loginsignupbuttonid);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://8d7df1d7.ngrok.io/postdata"; // your URL
        queue.start();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if(!email.equals("")&& !password.equals(""))
                {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("email", email);
                        obj.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final JsonObjectRequest jsObjRequest = new
                            JsonObjectRequest(Request.Method.POST,
                            url,
                            obj,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                         message= response.getString("result");
                                         Log.d(TAG, "Message is: " +message);
                                    } catch (Exception e) {
                                        //e.printStackTrace();
                                        System.out.println("IDHAR AYA");
                                    }
                                    if(message.equals("Success"))
                                    {
                                        SharedPreferences.Editor editor = getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit();
                                        editor.putBoolean("isLoggedIn",true);
                                        editor.apply();

                                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //textView.setText("That didn't work!");

                            Toast.makeText(LoginActivity.this, "Something Went Wrong volley error", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });
                    queue.add(jsObjRequest);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Make sure Email and Password fields are filled", Toast.LENGTH_SHORT).show();

                }

            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }
    @Override
    protected void onStop() {

        super.onStop();
        startService(new Intent(this, MyService.class));
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

}


