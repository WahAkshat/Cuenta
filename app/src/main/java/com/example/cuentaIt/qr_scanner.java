package com.example.cuentaIt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class qr_scanner extends AppCompatActivity {
    CodeScanner codescanner;
    CodeScannerView scannerview;
    TextView resultData;

    @Override
    protected void onResume() {
        super.onResume();
        requestforcamera();



    }
    private void requestforcamera(){
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                codescanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(qr_scanner.this, "CAMERA PERMISIION IS REQUIRED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
         scannerview=findViewById(R.id.scannerView);
         codescanner=new CodeScanner(this,scannerview);
         resultData=findViewById(R.id.results);
         codescanner.setDecodeCallback(new DecodeCallback() {
             @Override
             public void onDecoded(@NonNull Result result) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         resultData.setText(result.getText());
                     }
                 });
             }
         });
scannerview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        codescanner.startPreview();
    }
});

    }
}