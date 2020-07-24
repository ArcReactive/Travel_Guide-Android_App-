package com.example.appliccation01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
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
    private static final String POPULATION = "POPULATION";
    private static final String AREA = "AREA";
    private static final String NOTES = "NOTES";

    //table structure.
    private static final String CREATE_TABLE_CITY =
            "CREATE TABLE "+ DB_TABLE_CITY +"(" +
                    ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CITY +" TEXT, " +
                    COUNTRY + " TEXT, " +
                    POPULATION +" TEXT," +
                    AREA + " TEXT " +
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
    public Cursor viewDataCityPage(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "SELECT * FROM City WHERE CITY =?";

        return database.rawQuery(qry, new String[]{name});
    }

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
    public void insetDataCity(){
        SQLiteDatabase database = this.getReadableDatabase();
        String qry = "SELECT * FROM City WHERE COUNTRY =?";
        Cursor cursor = database.rawQuery(qry, new String[]{"France"});

        if(cursor.getCount()>0) {
            //nothing.
        }else{
            SQLiteDatabase database1 = this.getWritableDatabase();
            ContentValues contentValues1 = new ContentValues();

            contentValues1.put(CITY, "Paris");
            contentValues1.put(COUNTRY, "France");
            contentValues1.put(POPULATION,"2,148,271");
            contentValues1.put(AREA,"105.4 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Bordeaux");
            contentValues1.put(COUNTRY, "France");
            contentValues1.put(POPULATION,"254,436");
            contentValues1.put(AREA,"49.36 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Marseille");
            contentValues1.put(COUNTRY, "France");
            contentValues1.put(POPULATION,"869,815");
            contentValues1.put(AREA,"240.62 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Nantes");
            contentValues1.put(COUNTRY, "France");
            contentValues1.put(POPULATION,"309,346");
            contentValues1.put(AREA,"65.19 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Lyon");
            contentValues1.put(COUNTRY, "France");
            contentValues1.put(POPULATION,"513,275");
            contentValues1.put(AREA,"47.87 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "London");
            contentValues1.put(COUNTRY, "United Kingdom");
            contentValues1.put(POPULATION,"8,908,081");
            contentValues1.put(AREA,"1,572 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Birmingham");
            contentValues1.put(COUNTRY, "United Kingdom");
            contentValues1.put(POPULATION,"1,141,816");
            contentValues1.put(AREA,"267.8 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Liverpool");
            contentValues1.put(COUNTRY, "United Kingdom");
            contentValues1.put(POPULATION,"498,042");
            contentValues1.put(AREA,"111.8 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Nottingham");
            contentValues1.put(COUNTRY, "United Kingdom");
            contentValues1.put(POPULATION,"289,301");
            contentValues1.put(AREA,"74.61 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Sheffield");
            contentValues1.put(COUNTRY, "United Kingdom");
            contentValues1.put(POPULATION,"584,853");
            contentValues1.put(AREA,"367.94 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "NewYork");
            contentValues1.put(COUNTRY, "United State");
            contentValues1.put(POPULATION,"8.39M");
            contentValues1.put(AREA,"783.8 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Chicago");
            contentValues1.put(COUNTRY, "United State");
            contentValues1.put(POPULATION," 2.70M");
            contentValues1.put(AREA,"606.1 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Los angeles");
            contentValues1.put(COUNTRY, "United State");
            contentValues1.put(POPULATION,"3.99M");
            contentValues1.put(AREA,"1302 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "San fransisco");
            contentValues1.put(COUNTRY, "United State");
            contentValues1.put(POPULATION,"883,305");
            contentValues1.put(AREA,"121.4 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Washington");
            contentValues1.put(COUNTRY, "United State");
            contentValues1.put(POPULATION,"705,749");
            contentValues1.put(AREA,"177 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Beijing");
            contentValues1.put(COUNTRY, "China");
            contentValues1.put(POPULATION,"21.54M");
            contentValues1.put(AREA,"16808 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Shanghai");
            contentValues1.put(COUNTRY, "China");
            contentValues1.put(POPULATION,"24.28M");
            contentValues1.put(AREA,"6340 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Wuhan");
            contentValues1.put(COUNTRY, "China");
            contentValues1.put(POPULATION,"11.08M");
            contentValues1.put(AREA,"8494 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Tianjin");
            contentValues1.put(COUNTRY, "China");
            contentValues1.put(POPULATION,"11,558,000");
            contentValues1.put(AREA,"11760 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Guilin");
            contentValues1.put(COUNTRY, "China");
            contentValues1.put(POPULATION,"4.74M");
            contentValues1.put(AREA,"27809 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Mumbai");
            contentValues1.put(COUNTRY, "India");
            contentValues1.put(POPULATION,"18.41M");
            contentValues1.put(AREA,"603.4 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Chennai");
            contentValues1.put(COUNTRY, "India");
            contentValues1.put(POPULATION,"7.08M");
            contentValues1.put(AREA,"426 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Kolkata");
            contentValues1.put(COUNTRY, "India");
            contentValues1.put(POPULATION,"14.85M");
            contentValues1.put(AREA,"206.1 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "New delhi");
            contentValues1.put(COUNTRY, "India");
            contentValues1.put(POPULATION,"21.75M");
            contentValues1.put(AREA,"21.75M");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Pune");
            contentValues1.put(COUNTRY, "India");
            contentValues1.put(POPULATION,"3.11M");
            contentValues1.put(AREA,"331.3 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Melbourn");
            contentValues1.put(COUNTRY, "Asutralia");
            contentValues1.put(POPULATION,"4.93M");
            contentValues1.put(AREA,"9,992 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Sydney");
            contentValues1.put(COUNTRY, "Asutralia");
            contentValues1.put(POPULATION,"5.23M");
            contentValues1.put(AREA,"12,368 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Perth");
            contentValues1.put(COUNTRY, "Asutralia");
            contentValues1.put(POPULATION,"1.98M");
            contentValues1.put(AREA,"6,418 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Brisbane");
            contentValues1.put(COUNTRY, "Asutralia");
            contentValues1.put(POPULATION,"2.28M");
            contentValues1.put(AREA,"15,826 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Hobart");
            contentValues1.put(COUNTRY, "Asutralia");
            contentValues1.put(POPULATION,"206,097");
            contentValues1.put(AREA,"77.9 km2");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Tokyo");
            contentValues1.put(COUNTRY, "Japan");
            contentValues1.put(POPULATION,"9.27M");
            contentValues1.put(AREA,"622 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Osaka");
            contentValues1.put(COUNTRY, "Japan");
            contentValues1.put(POPULATION,"2.69M");
            contentValues1.put(AREA,"223 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Kyoto");
            contentValues1.put(COUNTRY, "Japan");
            contentValues1.put(POPULATION,"1.47M");
            contentValues1.put(AREA,"827.8 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Yokohama");
            contentValues1.put(COUNTRY, "Japan");
            contentValues1.put(POPULATION,"3.72M");
            contentValues1.put(AREA,"437.4 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Nagoya");
            contentValues1.put(COUNTRY, "Japan");
            contentValues1.put(POPULATION,"2.29M");
            contentValues1.put(AREA,"326.4 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "London");
            contentValues1.put(COUNTRY, "UK");
            contentValues1.put(POPULATION,"8.98M");
            contentValues1.put(AREA,"1,572 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Manchester");
            contentValues1.put(COUNTRY, "UK");
            contentValues1.put(POPULATION,"510,746");
            contentValues1.put(AREA,"115.6 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Liverpool");
            contentValues1.put(COUNTRY, "UK");
            contentValues1.put(POPULATION,"552,267");
            contentValues1.put(AREA,"111.8 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Glasgow");
            contentValues1.put(COUNTRY, "UK");
            contentValues1.put(POPULATION,"598,830");
            contentValues1.put(AREA,"175 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Bristol");
            contentValues1.put(COUNTRY, "UK");
            contentValues1.put(POPULATION,"535,907");
            contentValues1.put(AREA,"110 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Rome");
            contentValues1.put(COUNTRY, "Italy");
            contentValues1.put(POPULATION,"2.87M");
            contentValues1.put(AREA,"1,285 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Vanice");
            contentValues1.put(COUNTRY, "Italy");
            contentValues1.put(POPULATION,"825");
            contentValues1.put(AREA,"414.6 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Florence");
            contentValues1.put(COUNTRY, "Italy");
            contentValues1.put(POPULATION,"382,258");
            contentValues1.put(AREA,"102.4 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);


            contentValues1.put(CITY, "Milan");
            contentValues1.put(COUNTRY, "Italy");
            contentValues1.put(POPULATION,"1.35M");
            contentValues1.put(AREA,"181.8 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Berlin");
            contentValues1.put(COUNTRY, "Germany");
            contentValues1.put(POPULATION,"3.76M");
            contentValues1.put(AREA,"891.8 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Munich");
            contentValues1.put(COUNTRY, "Germany");
            contentValues1.put(POPULATION,"1.47M");
            contentValues1.put(AREA,"310.4 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Cologne");
            contentValues1.put(COUNTRY, "Germany");
            contentValues1.put(POPULATION,"1.06M");
            contentValues1.put(AREA,"405.2 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Dresden");
            contentValues1.put(COUNTRY, "Germany");
            contentValues1.put(POPULATION,"543,825");
            contentValues1.put(AREA,"328.8 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Nuremberg");
            contentValues1.put(COUNTRY, "Germany");
            contentValues1.put(POPULATION,"518,365");
            contentValues1.put(AREA,"186.5 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Madrid");
            contentValues1.put(COUNTRY, "Spain");
            contentValues1.put(POPULATION,"6.64M");
            contentValues1.put(AREA,"604.3 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Barcelona");
            contentValues1.put(COUNTRY, "Spain");
            contentValues1.put(POPULATION,"5.57M");
            contentValues1.put(AREA,"101.9 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Seville");
            contentValues1.put(COUNTRY, "Spain");
            contentValues1.put(POPULATION,"1.95M");
            contentValues1.put(AREA,"140.8 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Granada");
            contentValues1.put(COUNTRY, "Spain");
            contentValues1.put(POPULATION,"919,700");
            contentValues1.put(AREA,"919,700");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

            contentValues1.put(CITY, "Valencia");
            contentValues1.put(COUNTRY, "Spain");
            contentValues1.put(POPULATION,"2.54M");
            contentValues1.put(AREA,"134.6 km²");

            database1.insert(DB_TABLE_CITY, null,contentValues1);

        }
    }

}
