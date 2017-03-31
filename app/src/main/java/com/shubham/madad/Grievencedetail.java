package com.shubham.madad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Grievencedetail extends AppCompatActivity
{

    TextView male ,passno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievencedetail);

        male = (TextView)findViewById(R.id.male);
        passno = (TextView)findViewById(R.id.passno);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        final String imale =  extras.getString("male");
        final String ipassno =  extras.getString("passno");

        male.setText(imale);
        passno.setText(ipassno);



    }
}
