package com.example.ensias;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
        import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class recycleviewprofs  extends RecyclerView.Adapter<recycleviewprofs.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<professeurs> coursesArrayList;
    private Context context;

    // creating constructor for our adapter class
    public recycleviewprofs(ArrayList<professeurs> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public recycleviewprofs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.profitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull recycleviewprofs.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        professeurs courses = coursesArrayList.get(position);
        holder.courseNameTV.setText(courses.getLast());
        //System.err.println(courses.getLast());
        holder.courseDurationTV.setText(courses.getFirst());
        holder.courseDescTV.setText(courses.getNumero());
       /* holder.professorItemOption.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.professorItemOption);
            MenuInflater menuInflater = popupMenu.getMenuInflater();
            menuInflater.inflate(R.menu.item_option_menu, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.emailitem:
                        // define Intent object
                        // with action attribute as ACTION_SEND
                        Intent intent = new Intent(Intent.ACTION_SEND);

                        // add receiver to intent using putExtra function
                        intent.putExtra(Intent.EXTRA_EMAIL,
                                new String[] { courses.getEmail() });

                        // set type of intent
                        intent.setType("message/rfc822");

                        // startActivity with intent with chooser
                        // as Email client using createChooser function
                        context.startActivity(
                                Intent
                                        .createChooser(intent,
                                                "Choisir une app d'emailing :"));
                        break;

                    case R.id.edititem:
                        final Dialog dialog = new Dialog(context);
                        //We have added a title in the custom layout. So let's disable the default title.
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        //The user will be able to cancel the dialog by clicking anywhere outside the dialog.
                        dialog.setCancelable(true);
                        //Mention the name of the layout of your custom dialog.
                        dialog.setContentView(R.layout.edit_prof);

                        //Initializing the views of the dialog.
                        final EditText nom = dialog.findViewById(R.id.fullName);
                        final EditText departementList = dialog.findViewById(R.id.departementprof);
                        final EditText email = dialog.findViewById(R.id.email);
                        final EditText pwd = dialog.findViewById(R.id.password);
                        final ConstraintLayout modifier = dialog.findViewById(R.id.modifierprof);

                        //set edit texts with the old data
                        nom.setText(courses.getFirst());
                        departementList.setText(courses.getDepartement());
                        email.setText(courses.getEmail());
                        pwd.setText(courses.getPassword());

                        modifier.setOnClickListener(view1 -> {
                            //get the new data
                            String name = nom.getText().toString();
                            String departement = departementList.getText().toString();
                            String emailText = email.getText().toString();
                            String password = pwd.getText().toString();

                            //update professor object data
                            courses.setFullName(name);
                            courses.setDepartement(departement);
                            courses.setEmail(emailText);
                            courses.setPassword(password);

                            // we send data to firebase with specific document id.
                            // below line is use to get the collection of our Firebase Firestore.
                            db.collection("professeurs").
                                    // below line is use toset the id of
                                    // document where we have to perform
                                    // update operation.
                                            document(courses.getId()).

                                    // after setting our document id we are
                                    // passing our whole object class to it.
                                            set(courses).

                                    // after passing our object class we are
                                    // calling a method for on success listener.
                                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // on successful completion of this process
                                            // we are displaying the toast message.
                                            Toast.makeText(context, "Modifications effectuées avec succés.", Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                // inside on failure method we are
                                // displaying a failure message.
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Modification échouées.", Toast.LENGTH_LONG).show();
                                }
                            });

                            dialog.dismiss();
                        });

                        dialog.show();
                        break;
                    case R.id.deleteitem:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setMessage("Est-ce que vous voulez vraiment supprimer le professeur : "+courses.getFirst()+" ?")
                                .setCancelable(false)
                                .setPositiveButton("Oui", (dialogInterface, i) -> {
                                    dialogInterface.cancel();

                                    // below line is for getting the collection
                                    // where we are storing our courses.
                                    db.collection("professeurs").
                                            // after that we are getting the document
                                            // which we have to delete.
                                                    document(courses.getId()).

                                            // after passing the document id we are calling
                                            // delete method to delete this document.
                                                    delete().
                                            // after deleting call on complete listener
                                            // method to delete this data.
                                                    addOnCompleteListener(task -> {
                                                // inside on complete method we are checking
                                                // if the task is success or not.
                                                if (task.isSuccessful()) {
                                                    // this method is called when the task is success
                                                    // after deleting we are starting our MainActivity.
                                                    Toast.makeText(context, "le professeur a été bien supprimé.", Toast.LENGTH_SHORT).show();

                                                    //update recyclerview
                                                    profs.remove(position);
                                                    notifyItemRemoved(position);
                                                    notifyItemRangeChanged(position,profs.size());
                                                } else {
                                                    // if the delete operation is failed
                                                    // we are displaying a toast message.
                                                    Toast.makeText(context, "Suppression échouée. ", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                })

                                .setNegativeButton("Non", (dialogInterface, i) -> dialogInterface.cancel());
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;
                }
                return false;
            });
        });*/
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return coursesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView courseNameTV;
        private final TextView courseDurationTV;
        private final TextView courseDescTV; //professorItemOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            courseNameTV = itemView.findViewById(R.id.FIRDTNAME);
            courseDurationTV = itemView.findViewById(R.id.LASTNAME);
            courseDescTV = itemView.findViewById(R.id.PHONENUMBER);
           // professorItemOption = itemView.findViewById(R.id.professoritemoption);
        }
    }
}

