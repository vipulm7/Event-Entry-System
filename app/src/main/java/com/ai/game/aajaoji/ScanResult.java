package com.ai.game.aajaoji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScanResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        ((TextView)findViewById(R.id.textView)).setText(getIntent().getStringExtra("Result"));
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);
    }
}