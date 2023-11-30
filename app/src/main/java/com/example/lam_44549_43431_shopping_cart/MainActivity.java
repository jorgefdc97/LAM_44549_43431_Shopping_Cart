package com.example.lam_44549_43431_shopping_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_Handler db = new DB_Handler(this);
        db.deleteDB();
        if(!db.check_database_existance()) {
            db.add_product(new Product("Chiclete",10, 0));
            db.add_product(new Product("Pastilha",65, 0));
            db.add_product(new Product("Chicla",80, 0));
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Product> products = db.get_all_products();
        mAdapter = new MyAdapter(products);
        recyclerView.setAdapter(mAdapter);

    }
}