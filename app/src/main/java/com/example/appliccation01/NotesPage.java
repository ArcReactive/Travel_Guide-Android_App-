package com.example.appliccation01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotesPage extends AppCompatActivity {

    String countryName,notes = "Testing Note submit";
    DatabaseHelper dbHelper;
    Button submitBtn;
    EditText TheNote;
    ArrayList<String> notesArrayList;
    ArrayAdapter adapter;
    ListView NotesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_page);
        //handling null exception.
        initDatabaseHelper();

        notesArrayList = new ArrayList<>();
        Intent intent = getIntent();
        countryName = intent.getStringExtra("EXTRA_MESSAGE");

        submitBtn = findViewById(R.id.SubmitBtn);
        TheNote = findViewById(R.id.NoteInputText);
        NotesListView = findViewById(R.id.NoteListView);



        //viewing data to note list.
        viewDataToList();

        //add some notes by clicking.
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNote();
            }
        });


    }

    //correct null point Error.
    private void initDatabaseHelper(){

        if(dbHelper == null){
            dbHelper = new DatabaseHelper(this);
        }
    }

    private void viewDataToList() {
        Cursor cursor = dbHelper.viewDataNotes(countryName);

        if(cursor.getCount() == 0){
            Toast.makeText(NotesPage.this, " There is no notes.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                notesArrayList.add(cursor.getString(2));
            }

            //add data to list.
            adapter = new ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    notesArrayList
            );
            NotesListView.setAdapter(adapter);
        }

    }

    //inserting note.
    public void insertNote(){
        notes = TheNote.getText().toString(); //convert to string and declare.

        if(notes.equals("")){  //check the empty.
            Toast.makeText(this,  "Nothing written", Toast.LENGTH_SHORT).show();
        }else {
            if (dbHelper.insertDataNotes(countryName,notes)) {
                Toast.makeText(this,  "Added note to "+ countryName , Toast.LENGTH_SHORT).show();
                TheNote.setText("");
                adapter.notifyDataSetChanged();//to refresh list.but not working.
            }
        }

    }
}