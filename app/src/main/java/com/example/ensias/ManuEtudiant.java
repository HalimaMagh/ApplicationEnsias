package com.example.ensias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ManuEtudiant extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuetudiant);

    }
    public void email(View v){
        Intent intent=new Intent(ManuEtudiant.this,contacter.class);
        startActivity(intent);
    }
    public void profile(View view){
        Intent intent=new Intent(ManuEtudiant.this,profile.class);
        startActivity(intent);
    }




    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(ManuEtudiant.this,MainActivity.class);
        startActivity(intent);
    }

    public void pdf(View view){
        Intent intent=new Intent(ManuEtudiant.this,pdf.class);
        startActivity(intent);
    }
}
