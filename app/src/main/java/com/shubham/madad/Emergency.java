package com.shubham.madad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Emergency extends AppCompatActivity
{
    Spinner spinner;
    String d;
    ArrayList<contact> contactList;
    TextView t;
    String selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        contactList=new ArrayList<>();
        contactList.add(new contact(623562152,"Area1","Ajay virmani"));
        contactList.add(new contact(652362152,"Area2","Tarun sharma"));
        contactList.add(new contact(622012152,"Area3","Vinnet kumar"));
        contactList.add(new contact(621162152,"Area4","Ram tripathi"));
        t=(TextView) findViewById(R.id.textView);
        spinner = (Spinner) findViewById(R.id.spin1);
        ArrayList<String> list = new ArrayList<>();
        list.add("Country1");
        list.add("Country2");
        list.add("Country3");
        list.add("Country4");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d=parent.getItemAtPosition(position).toString();
                contact c = contactList.get(position);
                selectedCountry="Agent Name: "+c.getAgent()+"\n"+"Contact Number: "+c.getNumber()+"\n"+"Address: "+c.getAddress();
                t.setText(selectedCountry);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
