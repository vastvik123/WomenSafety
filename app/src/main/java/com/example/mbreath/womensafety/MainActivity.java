package com.example.mbreath.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);
        if(isLoggedIn){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

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
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
