package com.nuwansudusinghe.appliccation01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

//@Author NuwanSudusinghe.
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int version = 1 ;
    //table details.
    private static final String DB_NAME = "Guide.db";
    private static final String DB_TABLE_CITY = "City";
    private static final String DB_TABLE_FAV = "Favorite";
    private static final String DB_TABLE_NOTE = "Notes";
    //columns.
    private static final String ID = "ID";
    private static final String CITY = "CITY";
    private static final String COUNTRY = "COUNTRY";
//    private static final String POPULATION = "POPULATION";
//    private static final String AREA = "AREA";
    private static final String NOTES = "NOTES";

    //table structure.
    private static final String CREATE_TABLE_CITY =
            "CREATE TABLE "+ DB_TABLE_CITY +"(" +
                    ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CITY +" TEXT, " +
                    COUNTRY + " TEXT " +
//                    POPULATION +" TEXT," +
//                    AREA + " TEXT " +
                    ")";

    private static final String CREATE_TABLE_FAVORITE =
            "CREATE TABLE "+ DB_TABLE_FAV +"(" +
                    ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COUNTRY + " TEXT"+
                    ")";

    private static final String CREATE_TABLE_NOTES =
            "CREATE TABLE "+ DB_TABLE_NOTE +"(" +
                    ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COUNTRY + " TEXT,"+
                    NOTES + " TEXT"+
                    ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_FAVORITE);
        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_FAV);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NOTE);

        onCreate(db);
    }

    //insert data into favorite table.
    public boolean insertDataFavorite(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COUNTRY", name);

        long result = database.insert(DB_TABLE_FAV, null,contentValues);

        //if result = -1, data doesn't insert.
        return result != -1;
    }

    //check the country already existence in favorite table.
    public boolean checkCountry(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "SELECT COUNTRY FROM Favorite WHERE COUNTRY =?";
        Cursor cursor = database.rawQuery(qry, new String[]{name});

        //if false, data is already in table.
        return cursor.getCount() <= 0;
    }

    //view data to favorite list.
    public Cursor viewDataFavorite(){
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "SELECT * FROM Favorite";

        return database.rawQuery(qry,null);
    }

    //Delete favorite country form table.
    public  boolean deleteFavoriteCountry(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        String whereClause = "COUNTRY=?";
        String[] whereArgs = new String[] { String.valueOf(name) };

        database.delete(DB_TABLE_FAV, whereClause, whereArgs);

        return true;
    }


    //view countries details to list.
    public Cursor viewDataCities(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "SELECT * FROM City WHERE COUNTRY =?";

        return database.rawQuery(qry, new String[]{name});
    }
    //view cities details to list.
//    public Cursor viewDataCityPage(String name){
//        SQLiteDatabase database = this.getReadableDatabase();
//        String qry = "SELECT * FROM City WHERE CITY =?";
//
//        return database.rawQuery(qry, new String[]{name});
//    }

    //insert data into Notes table.
    public boolean insertDataNotes(String country, String note){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNTRY, country);
        contentValues.put(NOTES, note);

        long result = database.insert(DB_TABLE_NOTE, null,contentValues);

        //if result = -1, data doesn't insert.
        return result != -1;
    }

    //view data from notes table.
    public Cursor viewDataNotes(String Cname){
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "SELECT * FROM Notes WHERE COUNTRY =?";

        return database.rawQuery(qry, new String[]{Cname});
    }


//...........................................Database Inserting part...................................................................
    //insert data into city table. Auto.
public void insetDataCity(Context context){
    Log.d("Details-->", "START INSERTING DATA......");
    SQLiteDatabase database = this.getReadableDatabase();
    String qry = "SELECT * FROM City WHERE COUNTRY =?";
    Cursor cursor = database.rawQuery(qry, new String[]{"Sri Lanka"});

    if(cursor.getCount()>0) {
        //just skip the step.
        Log.d("Details-->", "ALREADY HAVE DATA......");
    }else{
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        try (SQLiteDatabase database1 = this.getWritableDatabase()) {
            Log.d("Details-->", "INSIDE THE DATABASE HELPER TRY......");

            //read JSON
            InputStream inputStream = context.getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            Type cityListType = new TypeToken<List<City>>() {
            }.getType();
            List<City> cityList = new Gson().fromJson(json, cityListType);

            //looping data
            if (cityList != null) {
                Log.d("Details-->", "BEFORE THE FOR LOOP......");
                for (City city : cityList) {
                    ContentValues values = new ContentValues();
                    values.put(CITY, city.getName());
                    values.put(COUNTRY, city.getCountry());

                    database1.insert(DB_TABLE_CITY, null, values);
                }
                Log.d("Details-->", "END OF THE FOR LOOP......");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ERROR-->", ""+e);
        } finally {
            Log.d("Details-->", "CLOSING DATABASE......");
        }
    }
}

}
