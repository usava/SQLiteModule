package com.example.cowboy.sqlitemodule;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    EditText etName = null;
    EditText etSurname = null;
    EditText etPhone = null;
    EditText etEmail = null;
    EditText etSkype = null;
    Button btnSubmit = null;
    TextView tvResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.et_main_name);
        etSurname = (EditText) findViewById(R.id.et_main_surname);
        etPhone = (EditText) findViewById(R.id.et_main_phone);
        etEmail = (EditText) findViewById(R.id.et_main_email);
        etSkype = (EditText) findViewById(R.id.et_main_skype);
        btnSubmit = (Button) findViewById(R.id.btn_main_submit);
        tvResult = (TextView) findViewById(R.id.tv_main_result);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(PersonContract.KEY_NAME, etName.getText().toString());
                cv.put(PersonContract.KEY_SURNAME, etSurname.getText().toString());
                cv.put(PersonContract.KEY_PHONE, etPhone.getText().toString());
                cv.put(PersonContract.KEY_MAIL, etEmail.getText().toString());
                cv.put(PersonContract.KEY_SKYPE, etSkype.getText().toString());

                getContentResolver().insert(DBContentProvider.PERSONS_CONTENT_URI, cv);

                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClicked(Object item) {
        //make toast
    }
}
