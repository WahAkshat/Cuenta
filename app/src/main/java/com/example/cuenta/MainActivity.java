package com.example.cuenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.userLog);
        b2 = (Button) findViewById(R.id.orgLog);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserLogin.class);
                Toast.makeText(MainActivity.this,"User Login",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OrgLogin.class);
                Toast.makeText(MainActivity.this,"Org Login",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}