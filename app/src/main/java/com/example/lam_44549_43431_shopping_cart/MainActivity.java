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
        db.reconstructDB();
        try {
            new ExecutorTask(executorService,mainThreadHandler,url,db);
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         /*
        MainActivity.db.add_product(new Product(1,"Produto1", 2, 1));
        MainActivity.db.add_product(new Product(2,"Produto2", 2, 1));
        MainActivity.db.add_product(new Product(3,"Produto3", 2, 1));
        MainActivity.db.add_product(new Product(4,"Produto4", 2, 0));
        MainActivity.db.add_product(new Product(5,"Produto5", 2, 0));
        MainActivity.db.add_product(new Product(6,"Produto6", 2, 0));
        MainActivity.db.add_product(new Product(7,"Produto7", 2, 0));
        MainActivity.db.add_product(new Product(8,"Produto8", 2, 0));
        MainActivity.db.add_product(new Product(9,"Produto9", 2, 0));
        MainActivity.db.add_product(new Product(10,"Produto10", 2, 1));

          */
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Product> products = db.get_all_products();
        updateAdapter(products);
        Log.e("PRODUCTS' LIST", products.toString());
    }
    public void onClickShowAll(View v){
        ArrayList<Product> products = db.get_all_products();
        Log.e("PRODUCTS' LIST", products.toString());
        updateAdapter(products);
    }
    public void onClickShowNotInCart(View v){
        ArrayList<Product> products = db.getAllProductsNotInCart();
        Log.e("PRODUCTS'NOT IN CART", products.toString());
        updateAdapter(products);
    }
    public void onClickShowInCart(View v){
        List<Product> products = db.getAllProductsInCart();
        Log.e("PRODUCTS' IN CART", products.toString());
        updateAdapter(products);
    }
    public void updateAdapter(List<Product> products){
        mAdapter = new MyAdapter(products);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}