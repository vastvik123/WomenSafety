package com.example.mbreath.womensafety;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
public class VerifyPhoneActivity extends AppCompatActivity {
    private static final String TAG = "VerifyPhoneActivity";
    private String code;

    private String mVerificationId;
    private String enteredOTP;
    private String mobile;

    //The edittext to input the code
    private EditText editTextCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        //initializing objects

        editTextCode = findViewById(R.id.editTextCode);


        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        mobile = intent.getStringExtra("mobile");
        Log.d(TAG, "OTP is "+code);


        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredOTP = editTextCode.getText().toString().trim();
                if (enteredOTP.isEmpty() || enteredOTP.length() < 4) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }
                else if(enteredOTP.equals(code))
                {
                    Log.d(TAG, "OTP VERIFIED");
                    Intent intent = new Intent(VerifyPhoneActivity.this, AddNumbersActivity.class);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(VerifyPhoneActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }}

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
