package com.example.db;

import com.example.db.tables.Words;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHander extends SQLiteOpenHelper {

	public MyDBHander(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "productDB.db";
	public static final String TABLE_PRODUCTS = "products";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PRODUCTNAME = "productname";
	public static final String COLUMN_QUANTITY = "quantity";
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
	             TABLE_PRODUCTS + "("
	             + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME 
	             + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
	      db.execSQL(CREATE_PRODUCTS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
	     onCreate(db);
	}

	
	public void addProduct(Words word) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, word.getWord());
        values.put(COLUMN_QUANTITY, word.getFingerPrint());
 
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
	}
	
	public Words findWord(String word) {
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + word + "\"";
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Words product = new Words();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			product.setID(Integer.parseInt(cursor.getString(0)));
			product.setWord(cursor.getString(1));
			product.setFingerPrint((cursor.getString(2)));
			cursor.close();
		} else {
			product = null;
		}
	        db.close();
		return product;
	}
	
	public Words findFingerPrint(String fp) {
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_QUANTITY + " =  \"" + fp + "\"";
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Words product = new Words();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			product.setID(Integer.parseInt(cursor.getString(0)));
			product.setWord(cursor.getString(1));
			product.setFingerPrint((cursor.getString(2)));
			cursor.close();
		} else {
			product = null;
		}
	        db.close();
		return product;
	}
	
} 