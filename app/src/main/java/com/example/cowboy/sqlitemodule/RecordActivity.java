package com.example.cowboy.sqlitemodule;

/**
 * Created by Cowboy on 06.11.2017.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RecordActivity extends AppCompatActivity implements ItemClickListener<Person> {
    ArrayList<Person> persons = new ArrayList<Person>();
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Cursor cursor = getContentResolver().query(DBContentProvider.PERSONS_CONTENT_URI, null, null, null, null);

        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                Person person = new Person();
                person.setName(cursor.getString(cursor.getColumnIndex(PersonContract.KEY_NAME)));
                person.setSurname(cursor.getString(cursor.getColumnIndex(PersonContract.KEY_SURNAME)));
                person.setPhone(cursor.getString(cursor.getColumnIndex(PersonContract.KEY_PHONE)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(PersonContract.KEY_MAIL)));
                person.setSkype(cursor.getString(cursor.getColumnIndex(PersonContract.KEY_SKYPE)));
                persons.add(person);
            }
        }

        rv = (RecyclerView) findViewById(R.id.rv_main);

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RecordAdapter adapter = new RecordAdapter(this, persons);
        rv.setAdapter(adapter);
        adapter.setData(this);
    }

    @Override
    public void onItemClicked(Person person) {
        //make toast
        Toast.makeText(this, person.toString(), Toast.LENGTH_SHORT).show();
    }


}

