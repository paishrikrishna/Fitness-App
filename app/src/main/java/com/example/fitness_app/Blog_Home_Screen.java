package com.example.fitness_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Blog_Home_Screen extends AppCompatActivity {

    blog_adapter category_adapter;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<blog_data> data_array = new ArrayList<blog_data>();

    int page = 1 , limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_home_screen);
        ImageView work = (ImageView) findViewById(R.id.workout);
        ImageView food = (ImageView) findViewById(R.id.food);
        ImageView suppliment = (ImageView) findViewById(R.id.suppliment);
        ImageView body = (ImageView) findViewById(R.id.body);

        nestedScrollView = findViewById(R.id.main_scroll);
        recyclerView = findViewById(R.id.blogs);
        progressBar = findViewById(R.id.progress_bar);


        category_adapter = new blog_adapter(Blog_Home_Screen.this,data_array);
        recyclerView.setLayoutManager(new LinearLayoutManager(Blog_Home_Screen.this));
        recyclerView.setAdapter(category_adapter);
        getdata(page,limit);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    page ++;
                    progressBar.setVisibility(View.VISIBLE);
                    getdata(page,limit);
                }
                else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

/*
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Workout blog section");
                Intent i = new Intent(getContext(), category_blog.class);
                getContext().startActivity(i);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Food blog section");
                Intent i = new Intent(getContext(), category_blog.class);
                getContext().startActivity(i);
            }
        });
        suppliment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Suppliment blog section");
                Intent i = new Intent(getContext(), category_blog.class);
                getContext().startActivity(i);
            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Body blog section");
                Intent i = new Intent(getContext(), category_blog.class);
                getContext().startActivity(i);
            }
        });

 */

    }



    private void toast(String s) {
        Toast.makeText(Blog_Home_Screen.this, s, Toast.LENGTH_SHORT).show();
    }
    private void getdata(int pag, int limit) {
        String type = "video";
        RequestQueue queue = Volley.newRequestQueue(Blog_Home_Screen.this);
        String url ="http://vw1.pythonanywhere.com/test/";
        //String url = "http://192.168.0.187:8000/test/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray la , n , t , url;

                        try {
                            JSONObject root = new JSONObject(response.toString());
                            //tv.setText(root.getString("status"));
                            n = root.getJSONArray("thumbnail");
                            t =  root.getJSONArray("type");
                            la =  root.getJSONArray("title");
                            url =  root.getJSONArray("url");


                            for(int j = 0; j<n.length();j++) {
                                data_array.add(new blog_data(String.valueOf(la.get(j)), String.valueOf(t.get(j)), String.valueOf(n.get(j)),String.valueOf(url.get(j)) ));
                            }

                            category_adapter.setItems(data_array);
                            category_adapter.notifyDataSetChanged();
                            page = limit;

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}