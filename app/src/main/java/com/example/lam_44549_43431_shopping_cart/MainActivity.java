package com.example.lam_44549_43431_shopping_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executorService;
    Handler mainThreadHandler;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;
    private final String url = "https://hostingalunos.upt.pt/~dam/produtos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_Handler db = new DB_Handler(this);
        executorService= Executors.newFixedThreadPool(1);
        mainThreadHandler= HandlerCompat.createAsync(Looper.getMainLooper());
        if(!db.check_database_existance()) {
            new ExecutorTask(executorService,mainThreadHandler,url,db);
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Product> products = db.get_all_products();
        mAdapter = new MyAdapter(products);
        recyclerView.setAdapter(mAdapter);

    }
}