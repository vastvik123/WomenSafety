package com.example.mbreath.womensafety;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

public class checkKeyListner extends AppCompatActivity {
    private String TAG = "checKkeyListner";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        while(true)
        {
            String x="";
            Log.d(TAG, "IDHAR AYA");
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Intent intent = new Intent(checkKeyListner.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
