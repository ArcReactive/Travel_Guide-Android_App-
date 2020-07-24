package com.example.appliccation01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//@Author NuwanSudusinghe.
public class CityActivity extends AppCompatActivity {

    String txt, countryName = "Empty";
    TextView countryTxt;
    DatabaseHelper dbHelper;
    ArrayList<String> cities;
    ArrayAdapter adapter;
    ListView citylistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);


        countryTxt = (TextView) findViewById(R.id.CountryNameTextView);
        citylistView = (ListView) findViewById(R.id.city_ListView);
        cities = new ArrayList<>();
        //set name of city.
        Intent intent = getIntent();
        countryName = intent.getStringExtra("EXTRA_MESSAGE");
        countryTxt.setText(countryName);


        //handling null exception.
        initDatabaseHelper();
        //get data to list.
        viewData();

        //go to the city page.
        citylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt = citylistView.getItemAtPosition(position).toString();
                goToCityPage();
            }
        });


    }

    private void goToCityPage() {
        Intent intentCity = new Intent(this,CityPageActivity.class);
        intentCity.putExtra("EXTRA_MESSAGE",txt);
        startActivity(intentCity);
    }

    //correct null point Error.
    private void initDatabaseHelper(){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper(this);
        }
    }


    //get list of cities.
    private void viewData() {
        Cursor cursor = dbHelper.viewDataCities(countryName);

        if(cursor.getCount() == 0){
            Toast.makeText(CityActivity.this, " There are no cities.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                cities.add(cursor.getString(1));
            }

            //add data to list.
            adapter = new ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cities
            );
            citylistView.setAdapter(adapter);
        }

    }


}