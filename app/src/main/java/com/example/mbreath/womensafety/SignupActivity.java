package com.example.mbreath.womensafety;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    private EditText editTextMobile;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextMobile = findViewById(R.id.editTextMobile);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://20ea69b9.ngrok.io/contact"; // your URL
        queue.start();

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }
                else {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("mobile", mobile);
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
                                        message= response.getString("OTP");
                                        Intent intent = new Intent(SignupActivity.this, VerifyPhoneActivity.class);
                                        intent.putExtra("code", message);
                                        intent.putExtra("mobile", mobile);
                                        startActivity(intent);
                                        finish();
                                        //Log.d(TAG, "Message is: " +message);
                                    } catch (Exception e) {
                                        //e.printStackTrace();
                                        System.out.println("IDHAR AYA");
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //textView.setText("That didn't work!");

                            Toast.makeText(SignupActivity.this, "Something Went Wrong volley error", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });
                    queue.add(jsObjRequest);
                }




            }
        });
    }

}
