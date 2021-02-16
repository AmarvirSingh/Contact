package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText name, number;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.contact_name);
        number = findViewById(R.id.conatctNumber);
        add = findViewById(R.id.add_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper helper = new DBhelper(AddActivity.this);
                Boolean bool = helper.addingContact(name.getText().toString(), number.getText().toString());
                if (bool == true) {
                    Toast.makeText(AddActivity.this, "Failed to add contact", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}