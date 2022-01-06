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

import com.bumptech.glide.Glide;
import com.example.story_reading_app.R;
import com.example.lib.model.StoryModel;
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

        TextView nameStory;
        ImageView imageView;

        if(this.resource == R.layout.item_story){
            // create value
            nameStory = view.findViewById(R.id.txtNameStory);
            TextView nameChapterLast = view.findViewById(R.id.txtChapterNumberLast);
            imageView = view.findViewById(R.id.imgImageStory);

            //add view
            StoryModel model = getItem(position);
            nameStory.setText(model.getName());
            if(model.getChapterNumberLast() != null){
                nameChapterLast.setText("Chương "+model.getChapterNumberLast());
            }else {
                nameChapterLast.setText("Chưa tạo");
            }
            Glide.with(this.context).load(model.getImage()).into(imageView);
        } else if(this.resource == R.layout.item_search){

            //
            nameStory = view.findViewById(R.id.txtNameSearchItem);
            TextView author = view.findViewById(R.id.txtAuthorSearchItem);
            TextView numChap = view.findViewById(R.id.txtNumChapSearchItem);
            TextView summary = view.findViewById(R.id.txtSummarySearchItem);
            imageView = view.findViewById(R.id.imgSearchItem);

            //add view
            StoryModel model = getItem(position);
            nameStory.setText(model.getName());
            author.setText("Tác giả: " + model.getAuthor());

            if(model.getChapterNumberLast() != null){
                numChap.setText("Tổng số chương: " + model.getChapterNumberLast());
            }else {
                numChap.setText("Tổng số chương: 0");
            }
            summary.setText("Tóm tắt: " + model.getSummaryContent());
            Glide.with(this.context).load(model.getImage()).into(imageView);
        }
        return view;
    }
}
