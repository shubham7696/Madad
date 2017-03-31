package com.shubham.madad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FetchGrievence extends AppCompatActivity
{

    ListView listView;
    ArrayList<UsersGrv>  useredata;
    DatabaseReference adminReference,mDatabaseReference1;
    FirebaseAuth mAuth;
    DatabaseReference mDatabaseReference;
    FeedAdapter adapter;
    String country;
    String  userId ;
    String datafetch,dataadhr;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_grievence);

        listView = (ListView) findViewById(R.id.grievedata);

        adapter = new FeedAdapter(getApplicationContext(), R.layout.grvfeed);

        useredata = new ArrayList<>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        userId = mAuth.getCurrentUser().getUid().trim();


        adminReference = FirebaseDatabase.getInstance().getReference().child("admins");
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference().child("users");



        adminReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    for (DataSnapshot dataSnapshot1: ds.getChildren()){
                        if (dataSnapshot1.getKey().trim().equals(userId)){
                            country=ds.getKey();
                        }
                    }
                }
                Query query1 =  mDatabaseReference1.orderByChild("UserGrievence/CurrentCountry").equalTo(country);

                query1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s)
                    {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {

                            if (ds.getKey().trim().equals("UserGrievence")) {
                                dataadhr = ds.child("AdharCardNumber").getValue(String.class);
                                datafetch = ds.child("Grivience").getValue(String.class);

                                useredata.add(new UsersGrv(datafetch, dataadhr));
                                adapter.addAll(useredata);
                                listView.setAdapter(adapter);
                            }

                        }
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                final String eid = useredata.get(i).adhar;
                DatabaseReference usersRef = mDatabaseReference.child("users");
                Query query1 = usersRef.orderByChild("UserGrievence/AdharCardNumber").equalTo(eid);

                    query1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s)
                    {

                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {

                            if (ds.getKey().equals("UserGrievence")) {

                                    if ((ds.child("AdharCardNumber").getValue(String.class)).equals(eid)) {

                                        String AdharCardNumber = ds.child("AdharCardNumber").getValue(String.class);
                                        String Country = ds.child("Country").getValue(String.class);
                                        String CurrentAdress = ds.child("CurrentAdress").getValue(String.class);
                                        String CurrentCountry = ds.child("CurrentCountry").getValue(String.class);
                                        String DateofBirth = ds.child("DateofBirth").getValue(String.class);
                                        String EmailId = ds.child("EmailId").getValue(String.class);
                                        String EmigrantIdNumber = ds.child("EmigrantIdNumber").getValue(String.class);
                                        String FistName = ds.child("FistName").getValue(String.class);
                                        String Gender = ds.child("Gender").getValue(String.class);
                                        String Grivience = ds.child("Grivience").getValue(String.class);
                                        String IsPersonSameNot = ds.child("IsPersonSameNot").getValue(String.class);
                                        String LastName = ds.child("LastName").getValue(String.class);
                                        String Mobilenumber = ds.child("Mobilenumber").getValue(String.class);
                                        String PassportNumber = ds.child("PassportNumber").getValue(String.class);
                                        String RecruitmentAgentName = ds.child("RecruitmentAgentName").getValue(String.class);


                                        System.out.println(Gender);
                                        System.out.println(PassportNumber);


                                        Intent i = new Intent(getApplicationContext(), Grievencedetail.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("male", Gender);
                                        extras.putString("passno", PassportNumber);

                                        i.putExtras(extras);
                                        startActivity(i);
                                    }
                            }

                        }



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



        /*        query2.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {


                        for(DataSnapshot ds:dataSnapshot.getChildren())

                        {

                            DataUploader signupData = ds.child("UserGrievence").getValue(DataUploader.class);


                            String AdharCardNumber = signupData.getAdharCardNumber();
                            String Country = signupData.getCountry();
                            String CurrentAdress = signupData.getCurrentAdress();
                            String CurrentCountry = signupData.getCurrentCountry();
                            String DateofBirth = signupData.getDateofBirth();
                            String EmailId = signupData.getEmailId();
                            String EmigrantIdNumber = signupData.getEmigrantIdNumber();
                            String FistName = signupData.getFistName();
                            String Gender = signupData.getGender();
                            String Grivience = signupData.getGrivience();
                            String IsPersonSameNot = signupData.getIsPersonSameNot();
                            String LastName = signupData.getLastName();
                            String Mobilenumber = signupData.getMobilenumber();
                            String PassportNumber = signupData.getPassportNumber();
                            String RecruitmentAgentName = signupData.getRecruitmentAgentName();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
*/



            }
        });

    }


    public  class UsersGrv
    {
        public  String grievence;
        public  String adhar;

        public  UsersGrv(String grievence,String adhar)
        {
            this.grievence = grievence ;
            this.adhar  = adhar;
        }
    }


    public  class  FeedAdapter extends ArrayAdapter<UsersGrv>
    {
        private  int resource;
        public  FeedAdapter(Context context , int resource)
        {
            super(context,resource);
            this.resource = resource;
        }
        public View getView(int position , View convertView , ViewGroup parent)
        {
            if(null  == convertView)
            {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView  = inflater.inflate(R.layout.grvfeed,null);
            }

            UsersGrv userdata = getItem(position);
            TextView grv = (TextView) convertView.findViewById(R.id.usergrv);

            TextView uradh = (TextView)convertView.findViewById(R.id.useradhr);


            uradh.setText(userdata.adhar);
            grv.setText(userdata.grievence);

            return convertView;
        }
    }
}
