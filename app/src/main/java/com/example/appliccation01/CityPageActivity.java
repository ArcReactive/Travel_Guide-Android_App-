package com.example.appliccation01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//@Author NuwanSudusinghe.
public class CityPageActivity extends AppCompatActivity {

    String cityName, population, area, Link;
    TextView cityTxt, populationTxt, areaTxt;
    DatabaseHelper dbHelper;
    Button wikipediaBtn,airportBtn, weatherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_page);

        //declaration.
        cityTxt = (TextView) findViewById(R.id.CityNameTextView);
        populationTxt = (TextView) findViewById(R.id.populationTxtView);
        areaTxt = (TextView)findViewById(R.id.areaTxtView);
        wikipediaBtn = findViewById(R.id.WikiBtn);
        airportBtn = findViewById(R.id.AirportBtn);
        weatherBtn = findViewById(R.id.WeatherBtn);

        //set city name.
        Intent intent = getIntent();
        cityName = intent.getStringExtra("EXTRA_MESSAGE");
        cityTxt.setText(cityName);

        //handling null exception.
        initDatabaseHelper();
        //view data.
        getDataView();
        populationTxt.setText(population);
        areaTxt.setText(area);

        //go to wikipedia.
        wikipediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://en.wikipedia.org/wiki/"+cityName;
                Uri web = Uri.parse(Link);
                Intent goToWiki = new Intent(Intent.ACTION_VIEW, web);
                if(goToWiki.resolveActivity(getPackageManager())!=null){
                    startActivity(goToWiki);
                }

            }
        });

        //go to airport.
        airportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://www.google.com/maps/search/"+cityName+"nearest+airport/@";
                Uri web = Uri.parse(Link);
                Intent goToWiki = new Intent(Intent.ACTION_VIEW, web);
                if(goToWiki.resolveActivity(getPackageManager())!=null){
                    startActivity(goToWiki);
                }
            }
        });

        //go to weather.
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weIntent = new Intent(CityPageActivity.this,WeatherPage.class);
                weIntent.putExtra("EXTRA_MESSAGE",cityName);
                startActivity(weIntent);
            }
        });

    }

    //correct null point Error.
    private void initDatabaseHelper(){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper(this);
        }
    }

    private void getDataView() {

        Cursor cursor = dbHelper.viewDataCityPage(cityName);
        if(cursor.moveToFirst()){
            population = cursor.getString(3);
            area = cursor.getString(4);
        }
        cursor.close();
    }


}