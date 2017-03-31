package com.shubham.madad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    ListView listView;
    FeedAdapter adapter;
    ArrayList<Users>  useredata;

    DatabaseReference mDatabaseReference;
    String emaildata;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //////////////////////////////////////////////////////


        listView = (ListView) findViewById(R.id.usersdata);
        adapter = new FeedAdapter(getApplicationContext(), R.layout.feed);
        useredata = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        mDatabaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    useredata.add(new Users(ds.getValue(String.class)));
                    adapter.addAll(useredata);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                  String eid = useredata.get(i).email;

                DatabaseReference usersRef = mDatabaseReference.getDatabase().getReference().child("users");

                Query query = usersRef.orderByChild("email").equalTo(eid);


                String fetchemailId = adapterView.getChildAt(i).toString();
                DatabaseReference usersRefd = mDatabaseReference.getDatabase().getReference().child("users");

                Query query1 = usersRef.orderByChild("profiledata/email").equalTo(eid);

                query1.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {

                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {

                            SignupData signupData = ds.child("profiledata").getValue(SignupData.class);
                            String  femail=    signupData.getEmail();
                            String  fdob =    signupData.getDob();
                            String  fname  =  signupData.getName();
                            String  fsurname=    signupData.getSurname();
                            String  fmob =    signupData.getMob();
                            Intent i = new Intent(getApplicationContext(),FetchUserData.class);
                            Bundle extras = new Bundle();
                            extras.putString("name",fname );
                            extras.putString("surname",fsurname);
                            extras.putString("dob",fdob);
                            extras.putString("mob", fmob);
                            extras.putString("email",femail);
                            i.putExtras(extras);
                            startActivity(i);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                    }
                });

            }});
        ////////////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    ///////////////////////////////////////////////

    public  class Users
    {
        public  String email;
        public  Users(String email)
        {
            this.email = email;
        }
    }


    public  class  FeedAdapter extends ArrayAdapter<Users>
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
                convertView  = inflater.inflate(R.layout.feed,null);
            }

            Users userdata = getItem(position);
            TextView email = (TextView) convertView.findViewById(R.id.users);

            email.setText(userdata.email);

            return convertView;
        }
    }






    //////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin__login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent i = new Intent(getApplicationContext(),FetchGrievence.class);
            startActivity(i);


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
