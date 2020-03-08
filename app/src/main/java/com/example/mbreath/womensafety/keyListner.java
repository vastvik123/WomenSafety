package com.example.mbreath.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class keyListner extends AppCompatActivity {

    public class checkKeyListner extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            while(true)
            {
                String x="";
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
}
