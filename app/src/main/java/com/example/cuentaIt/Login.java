package com.example.cuentaIt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    public String type;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private Button login;
    private Button create;
    private EditText email;
    private EditText password;

    public SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSp(getSharedPreferences("login",MODE_PRIVATE));
        setSp(getSharedPreferences("type",MODE_PRIVATE));

        if(getSp().getBoolean("logged",false)){
            if(getSp().getString("type","").equalsIgnoreCase("user"))
            startActivity(new Intent(Login.this, UserHome.class));
            else
                startActivity(new Intent(Login.this, OrgHome.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.Login_button);
        create = (Button) findViewById(R.id.CreateAccount);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){

                    String email_val = email.getText().toString();
                    String pass_val = password.getText().toString();
                    
                    login(email_val,pass_val,type);

                }
                else{

                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {

                    String email_val = email.getText().toString();
                    String pass_val = password.getText().toString();

                    if (type.equalsIgnoreCase("org")) {
                        Intent i = new Intent(Login.this, CreateOrg.class);
                        i.putExtra("email", email_val);
                        i.putExtra("password", pass_val);
                        i.putExtra("type",type);

                        getSp().edit().putBoolean("logged",true).apply();
                        getSp().edit().putString("type", type).apply();
                        startActivity(i);
                    }
                    else{
                        mAuth.createUserWithEmailAndPassword(email_val,pass_val).addOnCompleteListener(Login.this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(Login.this, "Created", Toast.LENGTH_LONG).show();
                                            getSp().edit().putBoolean("logged",true).apply();
                                            getSp().edit().putString("type", type).apply();
                                            startActivity(new Intent(Login.this, UserHome.class));
                                        }

                                    }
                                });

                    }

                }
            }
        });

        //FIREBASE

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();

                if(mUser!=null)
                    Toast.makeText(Login.this,"Hello user",Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(Login.this,"Not signed in!",Toast.LENGTH_LONG).show();

            }
        };

        //INTENT

        if (savedInstanceState == null) {
            Bundle ex = getIntent().getExtras();
            if (ex != null) {
                type = ex.getString("type");
                Log.d("TAG", type);
            }
        }
        else {
            type = (String) savedInstanceState.getSerializable("type");
        }
    }

    private void login(String email_val, String pass_val, String type) {

        mAuth.signInWithEmailAndPassword(email_val,pass_val).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"We in",Toast.LENGTH_LONG).show();
                    getSp().edit().putBoolean("logged",true).apply();
                    getSp().edit().putString("type", type).apply();
                    if(type.equalsIgnoreCase("user"))
                        startActivity(new Intent(Login.this, UserHome.class));
                    else
                        startActivity(new Intent(Login.this, OrgHome.class));


                }
                else{
                    Toast.makeText(Login.this,"Still not in",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_sign_out){
            mAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public SharedPreferences getSp() {
        return sp;
    }

    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }
}