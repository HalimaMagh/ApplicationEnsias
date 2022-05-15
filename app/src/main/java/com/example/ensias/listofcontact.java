package com.example.ensias;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class listofcontact extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter adapter;
    ArrayList<professeurs> profs;
    @Override
    protected void onCreate(Bundle bnde){
        super.onCreate(bnde);
      /*  setContentView(R.layout.contact);
        recyclerView=findViewById(R.id.profs);
        database= FirebaseDatabase.getInstance().getReference("professeurs");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profs=new ArrayList<>();
        adapter=new MyAdapter(profs,this);
        recyclerView.setAdapter(adapter);*/


    }
}
