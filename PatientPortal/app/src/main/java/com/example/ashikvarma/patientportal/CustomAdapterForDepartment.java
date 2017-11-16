package com.example.ashikvarma.patientportal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//import static com.example.ashikvarma.patientportal.R.drawable.img1;

public class CustomAdapterForDepartment extends RecyclerView.Adapter<CustomAdapterForDepartment.MyViewHolder> {

    ArrayList departmentNames;
    ArrayList personImages;
    Resources resources;
    Context context;
    public Activity context1;
    public String s;

    public CustomAdapterForDepartment(Context context, ArrayList departmentNames) {

        this.context = context;
        this.departmentNames = departmentNames;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.departmentnames, parent, false);
        context = parent.getContext();
// set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(CustomAdapterForDepartment.MyViewHolder holder, final int position) {
// set the data in items
        holder.name.setText(departmentNames.get(position).toString());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s = departmentNames.get(position).toString();


                Fragment fragment = new DoctorList();

                FragmentManager fragmentManager = ((DashboardActivity) context).getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("departmentName", s); //key and value
//set Fragmentclass Arguments
                fragment.setArguments(bundle);


                // this is basically context of the class
                fragmentTransaction.replace(R.id.fragment_department_id, fragment);
//                fragmentTransaction.remove();
                // fragmentTransaction.replace(R.id.fragment_department_id, fragment);
                //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return departmentNames.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);


        }
    }
}