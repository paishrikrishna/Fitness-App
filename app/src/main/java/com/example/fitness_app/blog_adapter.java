package com.example.fitness_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class blog_adapter extends RecyclerView.Adapter<blog_adapter.ViewHolder> {
    private ArrayList<blog_data> data_array;
    private Activity activity;
    private Context context;

    public blog_adapter(Activity activity , ArrayList<blog_data> category_blogs_data){

        this.data_array = category_blogs_data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_card,parent,false);
        return new ViewHolder(view);
    }


    public void setItems(ArrayList<blog_data> data) {
        this.data_array = data;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        blog_data data = data_array.get(position);

        Log.d("test",data.thumbnail);
        Log.d("test",data.titl_e);
        Log.d("test",data.type);
        Picasso.with(this.context).load(data.thumbnail).into(holder.imageView);
        holder.textView.setText(data.titl_e);
        if(data.type.contains("Blog")){
            holder.play.setVisibility(View.GONE);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), data.titl_e, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(view.getContext(),webview.class);
                in.putExtra("title", data.titl_e );
                in.putExtra("url", data.url );
                view.getContext().startActivity(in);
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), data.titl_e, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(view.getContext(),webview.class);
                in.putExtra("title", data.titl_e );
                in.putExtra("url", data.url );
                view.getContext().startActivity(in);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data_array.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ImageView play;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail);
            textView = itemView.findViewById(R.id.titl_e);
            play = itemView.findViewById(R.id.play);

        }
    }

}
