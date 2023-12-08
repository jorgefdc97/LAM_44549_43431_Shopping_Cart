package com.example.lam_44549_43431_shopping_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executorService;
    Handler mainThreadHandler;
    public static RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;
    public static Context context;
    public static DB_Handler db;
    private final String url = "https://hostingalunos.upt.pt/~dam/produtos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        db = new DB_Handler(this);
        executorService = Executors.newFixedThreadPool(1);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        //sleeping for 1 second to read url content and write in DB
        try {
            new ExecutorTask(executorService,mainThreadHandler,url,db);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Product> products = db.get_all_products();
        updateAdapter(products);
        Log.e("PRODUCTS' LIST", products.toString());
    }
    public void onClickShowAll(View v){
        ArrayList<Product> products = db.get_all_products();
        updateAdapter(products);
    }
    public void onClickShowNotInCart(View v){
        ArrayList<Product> products = db.getAllProductsNotInCart();
        updateAdapter(products);
    }
    public void onClickShowInCart(View v){
        List<Product> products = db.getAllProductsInCart();
        updateAdapter(products);
    }
    public void updateAdapter(List<Product> products){
        mAdapter = new MyAdapter(products);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}