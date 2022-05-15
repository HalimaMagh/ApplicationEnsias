package com.example.ensias;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.QuerySnapshot;

        import java.util.ArrayList;
        import java.util.List;

public class detailprofs extends AppCompatActivity {

    // creating variables for our recycler view,
    // array list, adapter, firebase firestore
    // and our progress bar.
    private RecyclerView courseRV;
    private ArrayList<professeurs> coursesArrayList;
    private recycleviewprofs courseRVAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;
    public void ajouter(View v){
        Intent intent=new Intent(detailprofs.this,AjouterProf.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailprof);

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        loadingPB = findViewById(R.id.idProgressBar);

        // initializing our variable for firebase
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();

        // creating our new array list
        coursesArrayList = new ArrayList<>();
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(new LinearLayoutManager(this));

        // adding our array list to our recycler view adapter class.
        courseRVAdapter = new recycleviewprofs(coursesArrayList, this);

        // setting adapter to our recycler view.
        courseRV.setAdapter(courseRVAdapter);

        // below line is use to get the data from Firebase Firestore.
        // previously we were saving data on a reference of Courses
        // now we will be getting the data from the same reference.
        System.err.println( db.collection("professeurs").get()+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        db.collection("professeurs").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            loadingPB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                professeurs c = d.toObject(professeurs.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                coursesArrayList.add(c);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            courseRVAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(detailprofs.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(detailprofs.this, "Fail to get the data."+""+e, Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
        });
    }
}
