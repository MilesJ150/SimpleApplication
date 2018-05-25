package com.example.miles.simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button storeButton, getButton;
    private EditText storeValue, getValue;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String currentValue; //most recently stored value


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeButton = (Button) findViewById(R.id.storeButton);
        getButton = (Button) findViewById(R.id.getButton);
        storeValue = (EditText) findViewById(R.id.storeValue);
        getValue = (EditText) findViewById(R.id.getValue);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("values");

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store value in Firebase
                String value = storeValue.getText().toString();
                int finalValue = Integer.parseInt(value);
                myRef.setValue(finalValue);
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and
                // every time data at this location is updated.
                currentValue = (String) Long.toString((Long) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: retrieve most recently stored value in Firebase
                getValue.setText(currentValue);
            }
        });
    }
}
