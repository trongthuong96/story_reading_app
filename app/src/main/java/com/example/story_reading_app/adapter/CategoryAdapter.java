package com.example.story_reading_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.story_reading_app.R;
import com.example.lib.model.CategoryModel;

public class CategoryAdapter extends ArrayAdapter<CategoryModel> {
    Activity context;
    int resource;

    public CategoryAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View view = layoutInflater.inflate(this.resource, null);

        TextView txtName = view.findViewById(R.id.txtNameCategory);
        TextView txtDescribes = view.findViewById(R.id.txtDescribes);

        CategoryModel model = getItem(position);
        txtName.setText(model.getName());
        txtDescribes.setText(model.getDescribes());

        return view;
    }
}
