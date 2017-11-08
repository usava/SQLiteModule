package com.example.cowboy.sqlitemodule;

/**
 * Created by Cowboy on 06.11.2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Svyatoslav on 21.06.2017.
 */

public class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mName;
    private TextView mSurname;

    private Context context;
    public Person person;

    public RecordViewHolder(Context context, View itemView) {
        super(itemView);

        this.mName = (TextView) itemView.findViewById(R.id.tv_main_title);
        this.mSurname = (TextView) itemView.findViewById(R.id.tv_main_singer);
        itemView.setOnClickListener(this);
        this.context = context;

    }

    public void onClick(View v){

        final String name = mName.getText().toString();
        final String surname = mSurname.getText().toString();
    }

    public void bindPerson(Person person){
        this.person = person;
        if(person != null){
            mName.setText(person.getName());
            mSurname.setText(person.getSurname());
        }
    }
}
