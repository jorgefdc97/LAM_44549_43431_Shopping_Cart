package com.example.lam_44549_43431_shopping_cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DB_Handler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PRODUCTS";
    private static final String TABLE_NAME= "products";

    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String QUANTITY = "quantity";
    private static final String BOUGHT = "bought";
    private final Context context;

    DB_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY,"
                + DESCRIPTION + " TEXT,"
                + QUANTITY + " INT,"
                + BOUGHT + " INT" + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
        Product.idNum=0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void add_product(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,product.getId());
        values.put(DESCRIPTION, product.getDescription());
        values.put(QUANTITY, product.getQuantity());
        values.put(BOUGHT, product.getBought());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Product get_product(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { ID, DESCRIPTION, QUANTITY, BOUGHT}, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        assert cursor != null;
        Product product = new Product(cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));

        return product;
    }

    public List<Product> get_all_products() {
        List<Product> productsList = new ArrayList<Product>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setDescription(cursor.getString(1));
                product.setQuantity(Integer.parseInt(cursor.getString(2)));
                product.setBought(Integer.parseInt(cursor.getString(3)));

                productsList.add(product);
            } while (cursor.moveToNext());
        }

        return productsList;
    }

    public int update_product(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DESCRIPTION, product.getDescription());
        values.put(QUANTITY, product.getQuantity());
        values.put(BOUGHT, product.getBought());

        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[]{String.valueOf(product.getId())});
    }

    public void delete_product(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
        db.close();
    }

    public boolean check_database_existance() {
        File dbFile = this.context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }
    public void deleteDB() {
        if(check_database_existance()) {
            File dbFile = this.context.getDatabasePath(DATABASE_NAME);
            dbFile.delete();
            Product.idNum=0;
        }
    }
    public void reconstructDB() {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
