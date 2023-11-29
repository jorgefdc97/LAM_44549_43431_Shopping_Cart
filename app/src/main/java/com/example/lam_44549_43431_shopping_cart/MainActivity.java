package com.example.lam_44549_43431_shopping_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        if(!db.doesDatabaseExist()) {
            db.adicionarContacto(new Contact(1, "Maria", "Porto"));
            db.adicionarContacto(new Contact(2, "Joaquim", "Coimbra"));
            db.adicionarContacto(new Contact(3, "Mariana", "Lisboa"));
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Contact> contacts = db.getAllContacts();
        mAdapter = new MyAdapter(contacts);
        recyclerView.setAdapter(mAdapter);

    }
}