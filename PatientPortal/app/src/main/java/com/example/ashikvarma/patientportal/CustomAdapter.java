package com.example.ashikvarma.patientportal;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashikvarma.patientportal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

//import static com.example.ashikvarma.patientportal.R.drawable.img1;
import static com.example.ashikvarma.patientportal.R.id.parent;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList personNames;
    ArrayList personImages;
    Resources resources;
    Context context;

    public CustomAdapter( Context context,ArrayList personNames, ArrayList personImages) {

        this.context=context;
        this.personNames = personNames;
        this.personImages = personImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        context = parent.getContext();
// set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder holder, final int position) {
// set the data in items
        holder.name.setText(personNames.get(position).toString());
        String temp=personImages.get(position).toString();

        int resID = this.context.getResources().getIdentifier(temp , "drawable", context.getPackageName());



        Picasso.with(context).load(resID).into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String doctorName = personNames.get(position).toString();


                Fragment fragment = new AppointmentForm();

                FragmentManager fragmentManager = ((DashboardActivity)context).getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("doctorName", doctorName); //key and value
//set Fragmentclass Arguments
                fragment.setArguments(bundle);



                // this is basically context of the class
                fragmentTransaction.replace(R.id.fragment_appointment_id, fragment);

                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);


        }
    }
}