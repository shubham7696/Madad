package com.shubham.madad;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity
{



    private DatabaseReference mDatabase;
    private DatabaseReference   userDatabaseReference;
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView;
    private int year, month, day;
    private  EditText name ,surname, dob,mob,email;
    private  EditText cnfpass,pass;
    private Button submit;
    private FirebaseAuth mAuth;
    ProgressDialog mProgress;
    SignupData data;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.WHITE);
        gd.setCornerRadius(10);
        gd.setStroke(2, Color.GRAY);



        GradientDrawable gd1 = new GradientDrawable();
        gd1.setColor(Color.WHITE);
        gd1.setCornerRadius(10);
        gd1.setStroke(2, Color.GRAY);



        GradientDrawable gd3 = new GradientDrawable();
        gd3.setColor(Color.WHITE);
        gd3.setCornerRadius(10);
        gd3.setStroke(2, Color.GRAY);


        GradientDrawable gd4 = new GradientDrawable();
        gd4.setColor(Color.WHITE);
        gd4.setCornerRadius(10);
        gd4.setStroke(2, Color.GRAY);

        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Color.WHITE);
        gd2.setCornerRadius(10);
        gd2.setStroke(2, Color.GRAY);



        GradientDrawable gd5 = new GradientDrawable();
        gd5.setColor(Color.WHITE);
        gd5.setCornerRadius(10);
        gd5.setStroke(2, Color.GRAY);



        GradientDrawable gd6 = new GradientDrawable();
        gd6.setColor(Color.WHITE);
        gd6.setCornerRadius(10);
        gd6.setStroke(2, Color.GRAY);


        GradientDrawable gd7 = new GradientDrawable();
        gd7.setColor(Color.WHITE);
        gd7.setCornerRadius(10);
        gd7.setStroke(2, Color.GRAY);

        GradientDrawable gd8 = new GradientDrawable();
        gd8.setColor(Color.WHITE);
        gd8.setCornerRadius(10);
        gd8.setStroke(2, Color.GRAY);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        dateView = (EditText)findViewById(R.id.date);
        dateView.setBackground(gd);
        name = (EditText)findViewById(R.id.name);
        name.setBackground(gd1);
        surname = (EditText)findViewById(R.id.surname);
        surname.setBackground(gd2);
        dob = (EditText)findViewById(R.id.date);
        dob.setBackground(gd3);
        mob = (EditText)findViewById(R.id.mobile);
        mob.setBackground(gd4);
        email = (EditText)findViewById(R.id.email);
        email.setBackground(gd5);
        pass = (EditText)findViewById(R.id.pass);
        pass.setBackground(gd6);
        cnfpass = (EditText)findViewById(R.id.cnfpass);
        cnfpass.setBackground(gd7);
        submit = (Button)findViewById(R.id.submit);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            year = calendar.get(Calendar.YEAR);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            month = calendar.get(Calendar.MONTH);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        showDate(year, month+1, day);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mProgress = new ProgressDialog(Signup.this);
                mProgress.setMessage("Fetching Info...");
                mProgress.show();
                submitdata();

            }
        });
    }

    public void submitdata()
    {
        final DatabaseReference usersRef=mDatabase.child("users");
        final DatabaseReference userRef=mDatabase.child("user");
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),cnfpass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
               if (task.isSuccessful())
               {
                       data  = new SignupData(name.getText().toString(),surname.getText().toString(),dob.getText().toString(),mob.getText().toString(),email.getText().toString());
                       DatabaseReference dbReference = usersRef.child(mAuth.getCurrentUser().getUid());
                       dbReference.child("profiledata").setValue(data);
                   DatabaseReference userdbReference = userRef.child(mAuth.getCurrentUser().getUid());
                   userdbReference.setValue(data.getEmail());

               }
            }
        });



    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day)
    {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
