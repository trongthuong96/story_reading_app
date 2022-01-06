package com.example.story_reading_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lib.model.ChapterModel;
import com.example.story_reading_app.R;

public class ChapterAdapter extends ArrayAdapter<ChapterModel> {

    Activity context;
    int resource;
    public ChapterAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View view = layoutInflater.inflate(this.resource, null);

        ChapterModel model = getItem(position);

        if(this.resource == R.layout.item_chapter_name){
            //khai bao
            TextView txtChapterItem = view.findViewById(R.id.txtChapterItem);

            //gan gia tri
            txtChapterItem.setText("Chương "+ model.getChapterNumber() + ": " + model.getName());

            //chapter of story read
            SharedPreferences sharedPref = this.context.getSharedPreferences("ChapterSave", context.MODE_PRIVATE);
            int chapterNumber = sharedPref.getInt(model.getStoryId().toString(),-1);
            if(chapterNumber == model.getChapterNumber()){
                txtChapterItem.setTypeface(Typeface.DEFAULT_BOLD);
            }
        } else if (this.resource == R.layout.item_chapter_detail){

            TextView txtChapterNameNumber = view.findViewById(R.id.txtChapterNameNumber);
            TextView txtChapterContent = view.findViewById(R.id.txtChapterContent);

            txtChapterNameNumber.setText("Chương "+ model.getChapterNumber() + ": " + model.getName());
            txtChapterContent.setText(model.getContent());
        }
        return view;
    }
}
