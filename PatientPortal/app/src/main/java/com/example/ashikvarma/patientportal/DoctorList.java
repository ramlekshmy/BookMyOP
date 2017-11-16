package com.example.ashikvarma.patientportal;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorList extends Fragment {
    View view;
    //    TextView txt2;
//    public String name;
//    public String img_url;
//    int indexing=0;
    public ArrayList<String> name_listing=new ArrayList<String>();
    public ArrayList<String> url_listing=new ArrayList<String>();
    public ArrayList<String> dpt_list=new ArrayList<String>();
    public CardView cv;
    public String departmentName;
    //    public String year;
//    public ListView lv;
//    private ArrayAdapter<String> arr;
//    public TextView txt;
    DatabaseReference rootRef= FirebaseDatabase.getInstance().getReference();
    //    public ArrayList<String> offers=new ArrayList<>();
//
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList personNames = new ArrayList<>();
    ArrayList personImages = new ArrayList<>();

    public DoctorList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        departmentName=getArguments().getString("departmentName");
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

        view=inflater.inflate(R.layout.fragment_appointment, container, false);
//         cv=(CardView)view.findViewById(card_view);
//        cv.setOnClickListener(this);
        DatabaseReference refOffers=FirebaseDatabase.getInstance().getReference().child("Doctors");
        refOffers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                HashSet<OfferList> set=new HashSet<OfferList>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    String dpt=(((DataSnapshot) i.next()).getValue()).toString();
                    String name = (((DataSnapshot) i.next()).getValue()).toString();
                    String img_url=(((DataSnapshot) i.next()).getValue()).toString();
                    if(departmentName.equals(dpt))
                    {
                        name_listing.add(name);
                        url_listing.add(img_url);

                   }


                    dpt_list.add(dpt);

//                    OfferList off=new OfferList(name,img_url);
//                    set.add(off);
//                    myValues.add(name);
//                    myValues1.add(year);
                }

                for( String arr:name_listing)
                {

                    personNames.add(arr);
                    name_listing.clear();
                }
                for( String arr1:url_listing)
                {

                    personImages.add(arr1);
                    url_listing.clear();
                }

                RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

                //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                CustomAdapter customAdapter = new CustomAdapter(getActivity(), personNames,personImages);
                recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
//                RecyclerViewAdapter adapter = new RecyclerViewAdapter(set);
//                RecyclerView myView =  (RecyclerView)view.findViewById(R.id.recyclerview);
//                myView.setHasFixedSize(true);
//                myView.setAdapter(adapter);
//                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                llm.setOrientation(LinearLayoutManager.VERTICAL);
//                myView.setLayoutManager(llm);

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



//        final ArrayList<String> myValues = new ArrayList<String>();
//        final ArrayList<String> myValues1 = new ArrayList<String>();

        //Populate the ArrayList with your own values
//        myValues.add("KitKat");
//        myValues.add("Lollipop");
//        myValues.add("Marshmallow");


//        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);

//            txt2=(TextView)view.findViewById(R.id.textView2);

        getActivity().setTitle("DoctorList");



//
//         arr=new ArrayAdapter(getActivity(),android.R.layout.simple_gallery_item,offers);
//
//        lv.setAdapter(arr);
//
//        refOffers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Iterator i = dataSnapshot.getChildren().iterator();
//                while (i.hasNext()) {
//                   name=(((DataSnapshot) i.next()).getKey());
//                    listing.add(name);
//                }
//                offers.clear();
//
//
//                arr.notifyDataSetChanged();
//            }
////
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


////        mAdapter = new MyRecyclerViewAdapter(getDataSet());
////        mRecyclerView.setAdapter(mAdapter);
//
//

        return view;
    }
//    public void onClick(View view)
//    {
//        if(view==cv)
//        {
//            startActivity(new Intent(getActivity(),Appointment.class));
//        }

   // }

}
