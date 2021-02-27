package com.example.cuentaIt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateOrg extends AppCompatActivity {

    private Button create;

    String email,password;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_org);

        create = findViewById(R.id.orgCreate);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (savedInstanceState == null) {
                    Bundle ex = getIntent().getExtras();
                    if (ex != null) {
                        email = ex.getString("email");
                        Log.d("TAG", email);
                        password = ex.getString("password");
                        Log.d("TAG", password);
                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CreateOrg.this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(CreateOrg.this, "Created", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(CreateOrg.this, OrgHome.class));
                                        }

                                    }
                                });
                    }
                }
                else {
                    email = (String) savedInstanceState.getSerializable("email");
                    password = (String) savedInstanceState.getSerializable("password");
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CreateOrg.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(CreateOrg.this, "Created", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(CreateOrg.this, OrgHome.class));
                                    }

                                }
                            });
                }

            }
        });
        //INTENT



    }

}