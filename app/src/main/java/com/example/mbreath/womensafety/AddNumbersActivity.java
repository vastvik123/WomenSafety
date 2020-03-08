package com.example.mbreath.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNumbersActivity extends AppCompatActivity {
    Button addNum ;
    private static final String TAG = "AddNumbersActivity";

    EditText[] numbers = new EditText[5];
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_numbers);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://20ea69b9.ngrok.io/addcontacts"; // your URL
        queue.start();
        addNum = findViewById(R.id.addnumid);
        numbers[0] = findViewById(R.id.contact1);
        numbers[1] = findViewById(R.id.contact2);
        numbers[2] = findViewById(R.id.contact3);
        numbers[3] = findViewById(R.id.contact4);
        numbers[4] = findViewById(R.id.contact5);
        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit();
                for (int i = 0; i < 5; i++) {
                    String s = "Contact" + i;
                    editor.putString(s, numbers[i].getText().toString().trim());
                    editor.apply();
                }

                        Intent intent = getIntent();
                        mobile = intent.getStringExtra("mobile");
                        Log.d(TAG, "Mobile is: "+mobile);
                        Log.d(TAG, "contact 1 : "+numbers[0]);
                Log.d(TAG, "contact 1 : "+numbers[0].getText().toString().trim());

                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("mobile", mobile);
                            obj.put("Contact1", numbers[0].getText().toString().trim());
                            obj.put("Contact2", numbers[1].getText().toString().trim());
                            obj.put("Contact3", numbers[2].getText().toString().trim());
                            obj.put("Contact4", numbers[3].getText().toString().trim());
                            obj.put("Contact5", numbers[4].getText().toString().trim());

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
                                            Intent intent = new Intent(AddNumbersActivity.this, HomeActivity.class);
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

                                Toast.makeText(AddNumbersActivity.this, "Something Went Wrong volley error", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
                        queue.add(jsObjRequest);
                    }
                });
            }
    }

