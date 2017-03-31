package com.shubham.madad;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class getInformation extends AppCompatActivity {
    EditText fName, agentName, lName, mobileNumber, Dob, passportdNumber, adharNumber, currentAdress;
    Button btSumit;
    RadioGroup rgSex, rgChoice;
    RadioButton yes, no, dontKnow, male, female,r1,r2,r3;
    Spinner spGrivenceSelection;
    private DatabaseReference mDatabase;
   // FirebaseAuth Mauth;
    EditText emigrantNumber,emailId;
    DatabaseReference rr;
    RadioGroup rg;
    ToggleButton tb;
    FirebaseAuth mAuth;
    String d;
    Spinner spinner;

    //---------------------------------------database variable not using it-----------------
    DataUploader db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_information);
      //  myref = FirebaseDatabase.getInstance().getReference();
        //-------------radio groups---------------------
        rg = (RadioGroup) findViewById(R.id.rg);             // for emigrant number
        rgSex = (RadioGroup) findViewById(R.id.rgSex);       // radio group for sex choice
        rgChoice = (RadioGroup) findViewById(R.id.rgChoice);  //radio group for choosing yes or no for agent name
        //--------------------------spinners-----------------
        spGrivenceSelection = (Spinner) findViewById(R.id.gcategeory);
        spinner = (Spinner) findViewById(R.id.spin1);

        Toolbar tb1 = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        Intent intent = getIntent();

        Bundle extras = intent.getExtras();


        final String address =  extras.getString("Address");
        final String country =  extras.getString("Country");

        //-------------------Radio Buttons--------------------

        yes = (RadioButton) findViewById(R.id.yes);
        no = (RadioButton) findViewById(R.id.no);
        dontKnow = (RadioButton) findViewById(R.id.dontKnow);

        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);

        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);




        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        Dob = (EditText) findViewById(R.id.dob);
        passportdNumber = (EditText) findViewById(R.id.passwordNumber);
        adharNumber = (EditText) findViewById(R.id.adharNumber);
        currentAdress = (EditText) findViewById(R.id.currentAdress);

        currentAdress.setText(address+country);

        emailId= (EditText) findViewById(R.id.emailId);
        tb = (ToggleButton) findViewById(R.id.toggleButton);
        btSumit = (Button) findViewById(R.id.submitButton);
//-------------dependent variables on choice of user----------------
        emigrantNumber = (EditText) findViewById(R.id.emirgantNumber);
        agentName = (EditText) findViewById(R.id.agentName);

        //    if(rgChoice.getCheckedRadioButtonId()==R.id.yes)
//             emigrantNumber.setVisibility(true);

        ArrayList<String> list = new ArrayList<>();
        list.add("Country1-city1");
        list.add("Country1-city2");
        list.add("Country1-city3");
        list.add("Country1-city4");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        db = new DataUploader();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());
               rr= mDatabase.child("UserGrievence");
        btSumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rr.child("FistName").setValue(fName.getText().toString());
                rr.child("LastName").setValue(lName.getText().toString());
                rr.child("AdharCardNumber").setValue(adharNumber.getText().toString());
                rr.child("Grivience").setValue(grivienceChoice());
                rr.child("Gender").setValue(genderOption());
                rr.child("Mobilenumber").setValue(mobileNumber.getText().toString());
                rr.child("PassportNumber").setValue(passportdNumber.getText().toString());
                rr.child("DateofBirth").setValue(Dob.getText().toString());
                rr.child("CurrentAdress").setValue(address);

                rr.child("CurrentCountry").setValue(country);

                if(rgChoice.getCheckedRadioButtonId()==R.id.yes)
                rr.child("RecruitmentAgentName").setValue(agentName.getText().toString());

                rr.child("EmailId").setValue(emailId.getText().toString());

                if(rg.getCheckedRadioButtonId()==R.id.r1)                   // if radio buttton yes is selected then emigrant id will save
                rr.child("EmigrantIdNumber").setValue(emigrantNumber.getText().toString());

                rr.child("IsPersonSameNot ").setValue(tb.getText().toString());
                rr.child("Country").setValue(spinner.getSelectedItem().toString());

            }
        });

            }



            String option() {
                if (rg.getCheckedRadioButtonId() == R.id.r1)
                    return "Yes";
                else if (rg.getCheckedRadioButtonId() == R.id.r2)
                    return "No";
                else
                    return "Don't know";
            }


      /*      public void setUserValues() {
                db.setGrivence(grivienceChoice());
                db.setLastName(fName.getText().toString());
                db.setFirstName(lName.getText().toString());
                db.setCurrentaddress(currentAdress.getText().toString());
                db.setMission(d);
      //          db.setGrivient(tb.getText().toString());
                db.setGender(gender());
                db.setAdharNumber(adharNumber.getText().toString());
                db.setRecruitingagentname(agentName.getText().toString());
      //          db.setPhone(mobileNumber);
                db.setMobile(mobileNumber.getText().toString());
                db.setEmail(emailId.getText().toString());
                db.setImmigrant(emigrantNumber.getText().toString());
                db.seteMigrateuse(option());
                db.setGrievantnamesameornot(tb.getText().toString());

            }

*/



   /* myref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("Database Error","Failed to read value.", databaseError.toException());

        }
    });*/



    public String genderOption() {
        if (rgSex.getCheckedRadioButtonId() == R.id.male)
            return "Male";
         else
        return "Female";
    }


    public String grivienceChoice()
    {
       return spGrivenceSelection.getSelectedItem().toString();
    }

}

