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

public class RecordViewHolder extends RecyclerView.ViewHolder{

    private TextView mName;
    private TextView mSurname;
    private TextView mPhone;
    private TextView mEmail;
    private TextView mSkype;

    private Context context;
    public Person person;

    public RecordViewHolder(Context context, View itemView) {
        super(itemView);

        this.mName = (TextView) itemView.findViewById(R.id.tv_main_name);
        this.mSurname = (TextView) itemView.findViewById(R.id.tv_main_surname);
        this.mPhone = (TextView) itemView.findViewById(R.id.tv_main_phone);
        this.mEmail = (TextView) itemView.findViewById(R.id.tv_main_email);
        this.mSkype = (TextView) itemView.findViewById(R.id.tv_main_skype);

        this.context = context;
    }

    public void bindPerson(Person person){
        this.person = person;
        if(person != null){
            mName.setText(person.getName());
            mSurname.setText(person.getSurname());
            mPhone.setText(person.getPhone());
            mEmail.setText(person.getEmail());
            mSkype.setText(person.getSkype());
        }
    }
}
