package com.damir.regattatracker.brod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BrodAdapter extends ArrayAdapter<Brod> {
    private final Context context;
    private final ArrayList<Brod> values;
    public BrodAdapter(@NonNull Context context, @NonNull ArrayList<Brod> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.values = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView naziv = (TextView) rowView.findViewById(android.R.id.text1);
        naziv.setText(values.get(position).getName());
        return rowView;
    }
}
