package com.example.story_reading_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.story_reading_app.R;
import com.example.lib.model.StoryModel;
import com.example.story_reading_app.fragment.HomeFragment;

import java.util.List;

public class StoryAdapter extends ArrayAdapter<StoryModel> {

    Activity context;
    int resource;

    public StoryAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity)context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View view = layoutInflater.inflate(this.resource, null);

        TextView nameStory = view.findViewById(R.id.txtNameStory);

        TextView nameChapterLast = view.findViewById(R.id.txtChapterNumberLast);
        ImageView imageView = view.findViewById(R.id.imgImageStory);

        StoryModel model = getItem(position);
        nameStory.setText(model.getName());
        if(model.getChapterNumberLast() != null){
            nameChapterLast.setText("Chương "+model.getChapterNumberLast());
        }else {
            nameChapterLast.setText("Chưa tạo");
        }

        Glide.with(this.context).load(model.getImage()).into(imageView);

        return view;
    }
}
