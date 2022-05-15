package com.example.ensias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class menuAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuadmin);}
    public void profs(View view){
        Intent i = new Intent(menuAdmin.this,detailprofs.class);
        startActivity(i);
    }
    public void etudiants(View view){
        Intent intent=new Intent(menuAdmin.this,detailprofs.class);
        startActivity(intent);
    }
    public void profile(View view){
        Intent intent=new Intent(menuAdmin.this,profile.class);
        startActivity(intent);
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
    }

    }
