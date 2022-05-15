package com.example.ensias;


import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.firestore.CollectionReference;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;

public class AjouterProf extends AppCompatActivity {

    // creating variables for our edit text
    private EditText first,last,dateDeNaissance,numero;

    // creating variable for button
    private Button submitCourseBtn;

    // creating a strings for storing
    // our values from edittext fields.
    private String first1,last1,dateDeNaissance1,numero1;

    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouterprof);

        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        // initializing our edittext and buttons
       first = findViewById(R.id.idEdtCourseName);
        last = findViewById(R.id.idEdtCourseDescription);
        numero = findViewById(R.id.idEdtCourseDuration);
        dateDeNaissance=findViewById(R.id.date);
        submitCourseBtn = findViewById(R.id.idBtnSubmitCourse);

        // adding on click listener for button
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                first1 = first.getText().toString();
                last1 = last.getText().toString();
                numero1 = numero.getText().toString();
                dateDeNaissance1=dateDeNaissance.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(first1)) {
                    first.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(last1)) {
                    last.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(numero1)) {
                    numero.setError("Please enter Course Duration");
                } else if (TextUtils.isEmpty(dateDeNaissance1)) {
                dateDeNaissance.setError("Please enter Course Duration");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(first1,last1,numero1,dateDeNaissance1);
                }
            }
        });
    }

    private void addDataToFirestore(String first, String last, String numero,String dateDeNaissance) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbCourses = db.collection("professeurs");

        // adding our data to our courses object class.

        professeurs courses = new professeurs(first,last,numero,dateDeNaissance) ;

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(courses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(AjouterProf.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(AjouterProf.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
