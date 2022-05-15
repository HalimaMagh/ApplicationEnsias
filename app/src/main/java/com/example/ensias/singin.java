package com.example.ensias;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class singin extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth=null;
    private EditText email;
    private EditText password;
    private Button buttonSingup;
private  Bundle bundle;
private ProgressBar br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=savedInstanceState;
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.singin);
        email=findViewById(R.id.singintxt);
        password=findViewById(R.id.passwordSing);
        buttonSingup=findViewById(R.id.Regester);
        auth=FirebaseAuth.getInstance();
        br=findViewById(R.id.chargemment);
        buttonSingup.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {

        //auth.createUserWithEmailAndPassword(""+em,""+pass);
        auth.createUserWithEmailAndPassword("salima@gmail.com","tahaja") .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    br.setVisibility(View.VISIBLE);
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();

                    Toast.makeText(singin.this, "your accunt was create.",
                            Toast.LENGTH_LONG).show();


                    email.setText("",null);
                    password.setText("",null);
                    br.setVisibility(View.INVISIBLE);
                   // updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(singin.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                   // updateUI(null);
                }
            }
        });

    }
}