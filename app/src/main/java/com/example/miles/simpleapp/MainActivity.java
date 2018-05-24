package com.example.miles.simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button storeButton, getButton;
    private EditText storeValue;
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeButton = (Button) findViewById(R.id.storeButton);
        getButton = (Button) findViewById(R.id.getButton);
        storeValue = (EditText) findViewById(R.id.storeValue);

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

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: retrieve most recently stored value in Firebase

            }
        });
    }
}
