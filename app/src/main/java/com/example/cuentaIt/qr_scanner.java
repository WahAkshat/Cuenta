package com.example.cuentaIt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class qr_scanner extends AppCompatActivity {
    CodeScanner codescanner;
    CodeScannerView scannerview;
    TextView resultData;
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


    }
}