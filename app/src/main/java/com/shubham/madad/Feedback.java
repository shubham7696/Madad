package com.shubham.madad;


        import java.util.ArrayList;
        import java.util.List;
        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import static android.R.attr.name;
        import static java.lang.String.*;

public class Feedback extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private Button btnSubmit;
private Button button2;
    private EditText description;
 private DatabaseReference mDatabase;

    FirebaseAuth mAuth;

    private  DatabaseReference rr;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        mAuth = FirebaseAuth.getInstance();
        addItemsOnSpinner1();

        addItemsOnSpinner2();
        addListenerOnButton();  
        addListenerOnSpinnerItemSelection();
     Toolbar tb=(Toolbar)findViewById(R.id.tb);
        setSupportActionBar(tb);
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();

        list.add("Select Feedback Type");
        //list.add("suggestion");
        //list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setPrompt("Select Feedback Type");

        spinner1.setAdapter(dataAdapter);


    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();

        list.add("appreciation");
        list.add("suggestion");
        //list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setPrompt("Feedback Type");
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new com.shubham.madad.CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        description=(EditText) findViewById(R.id.description);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
         button2=(Button)findViewById(R.id.button2);

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                description.setText("");
            }

        });
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            { mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());

// Creating new user node, which returns the unique key value
// new user node would be /users/$userid/


                rr   = mDatabase.child("UserFeedback");

// creating user object
                User user = new User(valueOf(spinner1.getSelectedItem()),
                        valueOf(spinner2.getSelectedItem()),description.getText().toString());

// pushing user to 'users' node using the userId
            //    mDatabase.child("users").child(userId).setValue(user);

                rr.setValue(user);


            }

        });
    }
}



