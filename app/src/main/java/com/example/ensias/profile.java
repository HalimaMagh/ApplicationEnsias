package com.example.ensias;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {
TextView text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        text=findViewById(R.id.modi);


    }
    public void modify(View view){
        text.setText("0611227279");
        Toast.makeText(this,"your number phone has modifed",Toast.LENGTH_LONG).show();


    }
}
