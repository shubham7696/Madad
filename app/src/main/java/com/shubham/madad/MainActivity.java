package com.shubham.madad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference database;
    DatabaseReference childReference;
    FirebaseAuth mAuth;
    EditText user;
    EditText pass;
    Button sub;
    String userid = "";

    TextView signup;
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance().getReference();

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





        Toolbar tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        user = (EditText) findViewById(R.id.user);
        user.setBackground(gd);

        pass = (EditText) findViewById(R.id.pass);
        pass.setBackground(gd1);

        sub = (Button) findViewById(R.id.sub);

        signup = (TextView)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent i  = new Intent(getApplicationContext(),Signup.class);
                startActivity(i);

            }
        });

        sub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                childReference = database.child("admin");

                mAuth.signInWithEmailAndPassword(user.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            final String userId = mAuth.getCurrentUser().getEmail();
                            childReference.addValueEventListener(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot)
                                {
                                    for (DataSnapshot ds :dataSnapshot.getChildren())
                                    {
                                        if (ds.getValue(String.class).equals(userId))
                                        {

                                            setFlag(true);
                                            Toast.makeText(MainActivity.this, "in if condition", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity.this, Admin_Login.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError)
                                {

                                }
                            });

                            if (!isFlag())
                            {
                             childReference = database.child("user");
                                childReference.addValueEventListener(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot)
                                    {
                                        for (DataSnapshot ds :dataSnapshot.getChildren())
                                        {
                                            if (ds.getValue(String.class).equals(userId))
                                            {
                                                Toast.makeText(MainActivity.this, "in if condition", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MainActivity.this, Student_Login.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError)
                                    {

                                    }
                                });
                            }
                    }
                }
            }

            );
        }
    }

    );


}
}
