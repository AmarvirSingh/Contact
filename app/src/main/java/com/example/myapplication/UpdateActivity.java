package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name, num;
    Button update, delete;

    // creating string variable for having data of the intent from the main activity

    String intentId, intentName, intentNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.updateName);
        num = findViewById(R.id.updateNumber);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String tempname = name.getText().toString();
               String tempnumber = num.getText().toString();

                DBhelper helper = new DBhelper(UpdateActivity.this);
                 boolean bool = helper.updateData(intentId,tempname, tempnumber);

                 if(bool == true){
                     Toast.makeText(UpdateActivity.this, "done", Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(UpdateActivity.this, "failed", Toast.LENGTH_SHORT).show();
                 }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper helper = new DBhelper(UpdateActivity.this);
                boolean bool = helper.deleteData(intentId);
                if(bool == true){
                    Toast.makeText(UpdateActivity.this, "done", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        getIntentData();

    }

    // disabling back button so that user can not click it
    @Override
    public void onBackPressed() {

    }


    void getIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("number") ){
            intentId = getIntent().getStringExtra("id");
            intentName = getIntent().getStringExtra("name");
            intentNumber = getIntent().getStringExtra("number");

            name.setText(String.valueOf(intentName));
            num.setText(String.valueOf(intentNumber));

        }
    }
}