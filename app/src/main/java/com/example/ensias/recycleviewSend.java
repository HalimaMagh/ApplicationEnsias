package com.example.ensias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleviewSend extends RecyclerView.Adapter<recycleviewSend.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<professeurs> coursesArrayList;
    private Context context;

    // creating constructor for our adapter class
    public recycleviewSend(ArrayList<professeurs> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public recycleviewSend.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new recycleviewSend.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull recycleviewSend.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        professeurs courses = coursesArrayList.get(position);
        holder.courseNameTV.setText(courses.getLast());
        //System.err.println(courses.getLast());
        holder.courseDurationTV.setText(courses.getFirst());
        holder.courseDescTV.setText(courses.getNumero());
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
            courseNameTV = itemView.findViewById(R.id.first);
            courseDurationTV = itemView.findViewById(R.id.last);
            courseDescTV = itemView.findViewById(R.id.numero);
            // professorItemOption = itemView.findViewById(R.id.professoritemoption);
        }
    }
}


