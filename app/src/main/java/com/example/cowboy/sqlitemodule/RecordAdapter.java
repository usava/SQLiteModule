package com.example.cowboy.sqlitemodule;

/**
 * Created by Cowboy on 06.11.2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Svyatoslav on 21.06.2017.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordViewHolder> {

    private Context context;
    private ArrayList<Person> persons;
    private ItemClickListener<Person> mListener;

    public RecordAdapter(Context context, ArrayList<Person> persons) {
        this.context = context;
        this.persons = persons;
    }



    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new RecordViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.bindPerson(person);
        if(mListener != null){
            mListener.onItemClicked(persons.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void setData(ItemClickListener<Person> icl){
        mListener = icl;
    }
}
