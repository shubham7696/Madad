package com.shubham.madad;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.R.attr.data;
import static android.R.attr.value;

public class Student_update extends AppCompatActivity
{
    EditText name1,name2,mobilenumber,emigrationnumber,adharnumber,dob,currentadress,agentname,emailid,passportnumber;
    RadioGroup gender;
    Button update,reset;

    RadioButton rbMale,rbFemale;
    DatabaseReference dd;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);
        dd = FirebaseDatabase.getInstance().getReference().child("users");

        //------------------layout importing------------------------
        rbFemale=(RadioButton)findViewById(R.id.rbFemale);
        rbMale=(RadioButton)findViewById(R.id.rbMale);
        name1=(EditText)findViewById(R.id.etFirstName);
        name2=(EditText)findViewById(R.id.etLastName);
        mobilenumber=(EditText)findViewById(R.id.etMobileNumber);
        emigrationnumber=(EditText)findViewById(R.id.etemirgantNumber);
        adharnumber=(EditText)findViewById(R.id.etAdharNumber);
        dob=(EditText)findViewById(R.id.etDob);
        currentadress=(EditText)findViewById(R.id.etCurrentAdress);
        agentname=(EditText)findViewById(R.id.etRecruitingAgentNumber);
        emailid=(EditText)findViewById(R.id.etEmailId);
        passportnumber=(EditText)findViewById(R.id.etPassportNumber);
        gender=(RadioGroup)findViewById(R.id.rbGroup);
        //-------------------------button importing or connecting---------
        reset=(Button)findViewById(R.id.btReset);
        update=(Button)findViewById(R.id.btUpdate);
        mAuth = FirebaseAuth.getInstance();

        final String userId = mAuth.getCurrentUser().getUid();


dd.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren()) {
            if (ds.getKey().equals(userId)) {
                for (DataSnapshot ds1 : ds.getChildren()) {
                    if (ds1.getKey().trim().equals("UserGrievence")) {

                        name1.setText(ds1.child("FistName").getValue(String.class));
                        name2.setText(ds1.child("LastName").getValue(String.class));
                        mobilenumber.setText(ds1.child("Mobilenumber").getValue(String.class));
                        emigrationnumber.setText(ds1.child("EmigrantIdNumber").getValue(String.class));
                        adharnumber.setText(ds1.child("AdharCardNumber").getValue(String.class));
                        dob.setText(ds1.child("DateofBirth").getValue(String.class));
                        currentadress.setText(ds1.child("CurrentAdress").getValue(String.class));
                        agentname.setText(ds1.child("RecruitmentAgentName").getValue(String.class));
                        emailid.setText(ds1.child("EmailId").getValue(String.class));
                        passportnumber.setText(ds1.child("PassportNumber").getValue(String.class));
                        String g = ds1.child("Gender").getValue(String.class);
                        if (g.equals("Male"))
                            rbMale.setChecked(true);
                        if (g.equals("Female"))
                            rbFemale.setChecked(true);
                    }
                }
            }
        }}

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});


       /* dd.addValueEventListener(new ValueEventListener()
        {


            final String userId = mAuth.getCurrentUser().getUid();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {



                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if (ds.getValue(String.class).equals(userId))
                    {


                        //Map<String, String> map = dataSnapshot.getValue(Map.class);
                        name1.setText(ds.child("FistName").getValue(String.class));
                        name2.setText(ds.child("LastName").getValue(String.class));
                        mobilenumber.setText(ds.child("Mobilenumber").getValue(String.class));
                        emigrationnumber.setText(ds.child("EmigrantIdNumber").getValue(String.class));
                        adharnumber.setText(ds.child("AdharCardNumber").getValue(String.class));
                        dob.setText(ds.child("DateofBirth").getValue(String.class));
                        currentadress.setText(ds.child("CurrentAdress").getValue(String.class));
                        agentname.setText(ds.child("RecruitmentAgentName").getValue(String.class));
                        emailid.setText(ds.child("EmailId").getValue(String.class));
                        passportnumber.setText(ds.child("PassportNumber").getValue(String.class));
                        String g = ds.child("Gender").getValue(String.class);
                        if (g.equals("Male"))
                            rbMale.setChecked(true);
                        if (g.equals("Female"))
                            rbFemale.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("error","Failled to read the data", databaseError.toException());
            }
        });*/


        //----------buttons working-----------

        dd= dd.push();

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dd.child("First Name").setValue(name1.getText().toString());
                dd.child("Last Name").setValue(name2.getText().toString());
                dd.child("Adhar Card Number").setValue(adharnumber.getText().toString());
                dd.child("Gender").setValue(functionGender());
                dd.child("Mobile Number").setValue(mobilenumber.getText().toString());
                dd.child("Passport Number").setValue(passportnumber.getText().toString());
                dd.child("Date of Birth").setValue(dob.getText().toString());
                dd.child("Current Adress").setValue(currentadress.getText().toString());
                dd.child("Recruitment Agent Name").setValue(agentname.getText().toString());
                dd.child("Email Id").setValue(emailid.getText().toString());
                dd.child("Emigrant Id Number").setValue(emigrationnumber.getText().toString());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1.setText("");
                name2.setText("");
                mobilenumber.setText("");
                emigrationnumber.setText("");
                adharnumber.setText("");
                dob.setText("");
                currentadress.setText("");
                agentname.setText("");
                emailid.setText("");
                passportnumber.setText("");
                //               gender.setOnCheckedChangeListener();
            }
        });

    }

    public String functionGender() {
        if (gender.getCheckedRadioButtonId() == R.id.rbMale)
            return "Male";
        else return "Female";
    }

}
