package com.example.cuentaIt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OrgLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_login);
        Button toqr=findViewById(R.id.toqru);
        toqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrgLogin.this, qr_generator.class);
                Toast.makeText(OrgLogin.this,"GENERATE QR",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}