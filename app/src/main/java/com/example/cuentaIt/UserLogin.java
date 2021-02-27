package com.example.cuentaIt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Button toqr=findViewById(R.id.buttonuser);
        toqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserLogin.this, qr_generator.class);
                Toast.makeText(UserLogin.this,"GENERATE QR",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}