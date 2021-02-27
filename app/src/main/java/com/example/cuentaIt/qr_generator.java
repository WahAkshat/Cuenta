package com.example.cuentaIt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qr_generator extends AppCompatActivity {
    EditText qrvalue;
    Button generatebutton,scanbutton;
    ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
        qrvalue=findViewById(R.id.qrinput);
        generatebutton=findViewById(R.id.generatebutton);
        scanbutton=findViewById(R.id.scanbutton);
        qrImage=findViewById(R.id.qrimage);

        generatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=qrvalue.getText().toString();
                if(data.isEmpty()){
                    qrvalue.setError("Value is required");
                }
                else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    try {
                        Bitmap qrBits = qrgEncoder.getBitmap();
                        qrImage.setImageBitmap(qrBits);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }    }
        });
    }
}