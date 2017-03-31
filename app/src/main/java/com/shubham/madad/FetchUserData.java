package com.shubham.madad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class FetchUserData extends AppCompatActivity
{
    TextView name ,surname,dob,email,mob;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_user_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView)findViewById(R.id.name);
        surname = (TextView)findViewById(R.id.surname);
        dob = (TextView)findViewById(R.id.dob);
        email = (TextView)findViewById(R.id.email);
        mob = (TextView)findViewById(R.id.mob);
        ok =(Button)findViewById(R.id.ok);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.WHITE);
        gd.setCornerRadius(10);
        gd.setStroke(2, Color.GRAY);



        GradientDrawable gd1 = new GradientDrawable();
        gd1.setColor(Color.WHITE);
        gd1.setCornerRadius(10);
        gd1.setStroke(2, Color.GRAY);


        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Color.WHITE);
        gd2.setCornerRadius(10);
        gd2.setStroke(2, Color.GRAY);

        GradientDrawable gd3 = new GradientDrawable();
        gd3.setColor(Color.WHITE);
        gd3.setCornerRadius(10);
        gd3.setStroke(2, Color.GRAY);



        GradientDrawable gd4 = new GradientDrawable();
        gd4.setColor(Color.WHITE);
        gd4.setCornerRadius(10);
        gd4.setStroke(2, Color.GRAY);



        GradientDrawable gd5 = new GradientDrawable();
        gd5.setColor(Color.WHITE);
        gd5.setCornerRadius(10);
        gd5.setStroke(2, Color.GRAY);

        final String iname =  extras.getString("name");
        final String isurname =  extras.getString("surname");
        final String idob =  extras.getString("dob");
        final String imob =  extras.getString("mob");
        final String iemail =  extras.getString("email");

        name.setText(iname);
        name.setBackground(gd);
        surname.setText(isurname);
        surname.setBackground(gd1);
        dob.setText(idob);
        dob.setBackground(gd2);
        email.setText(iemail);
        email.setBackground(gd3);
        mob.setText(imob);
        mob.setBackground(gd4);
        ok.setBackground(gd5);


    }
}
