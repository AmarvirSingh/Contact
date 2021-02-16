package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private Context context;
    private ArrayList<String> srnum, name, number;


    CustomAdapter(Context context, ArrayList srnum, ArrayList name, ArrayList number){
    this.context = context ;
    this.srnum = srnum;
    this.name = name;
    this.number = number;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_design,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.naem.setText(String.valueOf(name.get(position)));
        holder.number.setText(String.valueOf(number.get(position)));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(srnum.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("number",String.valueOf(number.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView naem , number;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        naem = itemView.findViewById(R.id.textView);
        number = itemView.findViewById(R.id.textView2);
        linearLayout = itemView.findViewById(R.id.row_Linear);

        }
    }
}
