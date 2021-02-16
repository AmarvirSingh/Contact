package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    FloatingActionButton add;

    TextView total;


    DBhelper db;

    ArrayList<String> srnum, name, num;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        add = findViewById(R.id.add);
        total = findViewById(R.id.total);


        db = new DBhelper(MainActivity.this);
        srnum = new ArrayList<>();
        name = new ArrayList<>();
        num = new ArrayList<>();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        customAdapter = new CustomAdapter(MainActivity.this,srnum, name,num);
        rv.setAdapter(customAdapter);

        displayData();

        total.setText("Total contacts "+srnum.size());
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

  // this is for shwoing delet pic on the Action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll){
            alertDialogMethod();

        }
        return super.onOptionsItemSelected(item);
    }

    void displayData(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0){

            Toast.makeText(this, "no data"  , Toast.LENGTH_SHORT).show();

        }else{
            while(cursor.moveToNext()){
                srnum.add(cursor.getString(0));
                name.add(cursor.getString(1));
                num.add(cursor.getString(2));
            }
        }
    }

    // alert methods to show alert box

    void alertDialogMethod(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all");
        builder.setMessage("Are you sure ??? ");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                DBhelper helper = new DBhelper(MainActivity.this);
                helper.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}