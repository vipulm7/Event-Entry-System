package com.ai.game.aajaoji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.zxing.Result;

public class ScanActivity extends AppCompatActivity {

    private CodeScanner scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        int allPermissions = 1;
        String Permissions[] = {
                Manifest.permission.CAMERA
        };

        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, allPermissions);
        }
        else{
            runCodeScanner();
            // testing
        }
    }

    public void runCodeScanner() {
        scanner = new CodeScanner(this, findViewById(R.id.scanner_view));
        scanner.setAutoFocusEnabled(true);
        scanner.setFormats(CodeScanner.ALL_FORMATS);
        scanner.setScanMode(ScanMode.CONTINUOUS);

        scanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                      Result --> Hash Value
                        Intent intent = new Intent(getApplicationContext(), ScanResult.class);
                        intent.putExtra("Result", result.getText());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public static boolean hasPermissions(Context context, String... permissions){
        if(context != null && permissions != null){
            for (String permission : permissions){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        scanner.startPreview();
    }

    @Override
    public void onPause() {
        scanner.releaseResources();
        super.onPause();
    }
}