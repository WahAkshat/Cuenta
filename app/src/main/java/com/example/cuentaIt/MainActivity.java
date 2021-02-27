package com.example.cuentaIt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;

//    public SharedPreferences sp;
//
//    Login l = new Login();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sp = l.getSp();
//        if(l.getSp().getBoolean("logged",false)){
//            Log.d("tag","yes");
//            if(l.getSp().getString("type","").equalsIgnoreCase("user"))
//                startActivity(new Intent(MainActivity.this, UserHome.class));
//            else
//                startActivity(new Intent(MainActivity.this, OrgHome.class));
//        }


        b1 = (Button) findViewById(R.id.userLog);
        b2 = (Button) findViewById(R.id.orgLog);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hiiiiiiiiiiiii");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                i.putExtra("type","user");
                Toast.makeText(MainActivity.this,"User Login",Toast.LENGTH_LONG).show();

                startActivity(i);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                i.putExtra("type","org");
                Toast.makeText(MainActivity.this,"Org Login",Toast.LENGTH_LONG).show();

                startActivity(i);

            }
        });
    }
}