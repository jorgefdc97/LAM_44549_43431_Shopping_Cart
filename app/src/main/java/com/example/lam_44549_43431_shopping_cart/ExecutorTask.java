package com.example.lam_44549_43431_shopping_cart;

import android.os.Handler;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ExecutorTask {
    ExecutorService executor;

    Handler resultHandler;

    DB_Handler db;

    public ExecutorTask(ExecutorService executor, Handler resultHandler, String url, DB_Handler db) {
        this.executor=executor;
        this.resultHandler=resultHandler;
        this.db=db;
        this.executor.execute(new Runnable(){
            @Override
            public void run() {
                doWork(url);
            }
        });
    }
    public void doWork(String url) {
        db.reconstructDB();
        HttpHandler handler;
        handler= new HttpHandler();
        ArrayList<String> products = new ArrayList<>();
        products=handler.getProducts(url);
        for(String product:products) {
            db.add_product(new Product(product));
        }
    }
}
