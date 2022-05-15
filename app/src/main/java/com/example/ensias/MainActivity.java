package com.example.ensias;

import static android.content.ContentValues.TAG;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button loginBtn;
    EditText login;
    EditText password;
    Button singIn;
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn=findViewById(R.id.loginButton);
        login=findViewById(R.id.email_txt);
        password=findViewById(R.id.password_txt);
        singIn=findViewById(R.id.SingButton);
        mAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(this);
        singIn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if(view.getId() == loginBtn.getId()){
        mAuth.signInWithEmailAndPassword(login.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(MainActivity.this, ManuEtudiant.class);
                            startActivity(intent);


                            // updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });}
        else {

            Intent intent=new Intent(this, singin.class);
           startActivity(intent);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           String var=currentUser.getEmail();
        }else{
            Toast.makeText(MainActivity.this,"please sing up you don't have an accunt ",Toast.LENGTH_LONG);
        }
    }




}